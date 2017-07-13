package com.acuity.api.meta.tile_dumper;

import com.acuity.api.AcuityInstance;
import com.acuity.api.rs.utils.Scene;
import com.acuity.db.AcuityDB;
import com.acuity.rs.api.RSCollisionData;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mongodb.BasicDBObject;
import com.mongodb.WriteConcern;
import com.mongodb.client.model.BulkWriteOptions;
import com.mongodb.client.model.InsertManyOptions;
import com.mongodb.client.model.UpdateOneModel;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
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
        Stream.Builder<DumpTile> collected = Stream.builder();
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
                            collected.add(dumpTile);
                        }
                    }
                }
            }
        }


        executor.execute(() -> {
            List<UpdateOneModel<Document>> collect = collected.build().map(document -> new UpdateOneModel<Document>(
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
