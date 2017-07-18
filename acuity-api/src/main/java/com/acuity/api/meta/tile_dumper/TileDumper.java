package com.acuity.api.meta.tile_dumper;

import com.acuity.api.AcuityInstance;
import com.acuity.api.rs.query.Npcs;
import com.acuity.api.rs.query.SceneElements;
import com.acuity.api.rs.utils.Scene;
import com.acuity.api.rs.wrappers.common.locations.SceneLocation;
import com.acuity.db.AcuityDB;
import com.acuity.rs.api.RSCollisionData;
import com.mongodb.client.model.UpdateOneModel;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Zach on 7/12/2017.
 */
public class TileDumper {

    private static Logger logger = LoggerFactory.getLogger(TileDumper.class);
    private static MongoCollection tileData;
    private static Executor executor;

    static {
        try {
            AcuityDB.init();
            Jongo jongo = new Jongo(AcuityDB.getMongoClient().getDB("TileDB"));
            tileData = jongo.getCollection("TileData");
            executor = Executors.newSingleThreadExecutor();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void execute(){
        Stream.Builder<DumpTile> collectedTiles = Stream.builder();
        Stream.Builder<DumpSE> collectedSEs = Stream.builder();
        Stream.Builder<DumpNPC> collectedNPCs = Stream.builder();

        int baseX = Scene.getBaseX();
        int baseY = Scene.getBaseY();
        int plane = Scene.getPlane();
        RSCollisionData rsCollisionData = AcuityInstance.getClient().getRsClient().getCollisionMaps()[plane];

        if (rsCollisionData != null){
            int[][] flags = rsCollisionData.getFlags();
            for (int x = 3; x < flags.length; x++) {
                int[] tiles = flags[x];
                for (int y = 3; y < tiles.length; y++) {
                    if (x <= 98 && y <= 98){
                        DumpTile dumpTile = new DumpTile(x + baseX, y + baseY, plane, tiles[y]);
                        if (dumpTile.getFlag() != 0){
                            collectedTiles.add(dumpTile);
                        }

                        SceneElements.streamLoaded(x, y, plane)
                                .forEach(sceneElement -> collectedSEs.add(new DumpSE(sceneElement, dumpTile.getX(), dumpTile.getY(), dumpTile.getPlane())));

                        Npcs.streamLoaded(new SceneLocation(x, y, plane))
                                .forEach(npc -> collectedNPCs.add(new DumpNPC(npc, dumpTile.getX(), dumpTile.getY(), dumpTile.getPlane())));
                    }
                }
            }
        }

        executor.execute(() -> {
            List<UpdateOneModel<Document>> collect = collectedNPCs.build().map(document -> new UpdateOneModel<Document>(
                            new Document("_id", document.getID()),
                            document.toUpdate(),
                            new UpdateOptions().upsert(true)
                    )
            ).collect(Collectors.toList());
            logger.debug("Saving {} dumped SEs.", collect.size());
            AcuityDB.getMongoClient().getDatabase("TileDB").getCollection("NPCData").bulkWrite(collect);
        });

        executor.execute(() -> {
            List<UpdateOneModel<Document>> collect = collectedSEs.build().map(document -> new UpdateOneModel<Document>(
                            new Document("_id", document.getID()),
                            document.toUpdate(),
                            new UpdateOptions().upsert(true)
                    )
            ).collect(Collectors.toList());
            logger.debug("Saving {} dumped SEs.", collect.size());
            AcuityDB.getMongoClient().getDatabase("TileDB").getCollection("SEData").bulkWrite(collect);
        });

        executor.execute(() -> {
            List<UpdateOneModel<Document>> collect = collectedTiles.build().map(document -> new UpdateOneModel<Document>(
                    new Document("_id", document.getID()),
                    document.toUpdate(),
                    new UpdateOptions().upsert(true)
            )
            ).collect(Collectors.toList());
            logger.debug("Saving {} dumped tiles.", collect.size());
            AcuityDB.getMongoClient().getDatabase("TileDB").getCollection("TileData").bulkWrite(collect);
        });
    }

    public static void clear() {
        tileData.drop();
    }
}
