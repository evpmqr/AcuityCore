package com.acuity.api.meta.tile_dumper;

import com.acuity.api.AcuityInstance;
import com.acuity.api.rs.query.Npcs;
import com.acuity.api.rs.query.SceneElements;
import com.acuity.api.rs.utils.Scene;
import com.acuity.rs.api.RSCollisionData;
import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;
import org.jongo.MongoCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

/**
 * Created by Zach on 7/12/2017.
 */
public class TileDumper {

    private static Logger logger = LoggerFactory.getLogger(TileDumper.class);
    private static MongoCollection tileData;
    private static Executor executor;

    static {
     /*   try {
            AcuityDB.init();
            Jongo jongo = new Jongo(AcuityDB.getMongoClient().getDB("TileDB"));
            tileData = jongo.getCollection("TileData");
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        executor = Executors.newSingleThreadExecutor();
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
                    }
                }
            }
        }

        Npcs.streamLoaded().forEach(npc -> collectedNPCs.add(new DumpNPC(npc, npc.getWorldLocation().getWorldX(), npc.getWorldLocation().getWorldY(), npc.getWorldLocation().getPlane())));

        executor.execute(() -> {
            logger.info("Starting dump");
            OrientGraph graph = new OrientGraph("remote:acuitybotting.com/MapData", "root", "");
            try {

                Object deleteTiles = graph.command(new OCommandSQL("delete vertex from Tile where plane = " + plane + " and x >= " + (baseX + 3) + " and x <= " + (baseX + 98) + " and y >= " + (baseY + 3) + " and y <= " + (baseY + 98))).execute();
                Object deleteNpcs = graph.command(new OCommandSQL("delete vertex from NPC where plane = " + plane + " and x >= " + (baseX + 3) + " and x <= " + (baseX + 98) + " and y >= " + (baseY + 3) + " and y <= " + (baseY + 98))).execute();
                Object deleteSEs = graph.command(new OCommandSQL("delete vertex from SceneElement where plane = " + plane + " and x >= " + (baseX + 3) + " and x <= " + (baseX + 98) + " and y >= " + (baseY + 3) + " and y <= " + (baseY + 98))).execute();


                logger.debug("Deleted {} tiles.", deleteTiles);
                logger.debug("Deleted {} npcs.", deleteNpcs);
                logger.debug("Deleted {} scene elements.", deleteSEs);

                OrientVertex capture = graph.addVertex("class:Capture", "lowerX", baseX + 3, "lowerY", baseY + 3, "upperX", baseX + 98, "upperY", baseY + 98, "plane", plane, "timestamp", System.currentTimeMillis());

                collectedTiles.build().forEach(dumpTile -> {
                    Map<String, Object> vertexProps = new HashMap<>();
                    vertexProps.put("x", dumpTile.getX());
                    vertexProps.put("y", dumpTile.getY());
                    vertexProps.put("plane", dumpTile.getPlane());
                    vertexProps.put("flag", dumpTile.getFlag());
                    OrientVertex orientVertex = graph.addVertex("class:Tile", vertexProps);
                    capture.addEdge(null, orientVertex, "class:captured");
                });

                collectedSEs.build().forEach(dumpSE -> {
                    Map<String, Object> vertexProps = new HashMap<>();
                    vertexProps.put("x", dumpSE.getX());
                    vertexProps.put("y", dumpSE.getY());
                    vertexProps.put("plane", dumpSE.getZ());
                    vertexProps.put("name", dumpSE.getName());
                    vertexProps.put("actions", dumpSE.getActions());
                    vertexProps.put("orientation", dumpSE.getRotation());
                    vertexProps.put("sceneElementID", dumpSE.getSeID());
                    OrientVertex orientVertex = graph.addVertex("class:SceneElement", vertexProps);
                    capture.addEdge(null, orientVertex, "class:captured");

                });

                collectedNPCs.build().forEach(dumpNPC -> {
                    Map<String, Object> vertexProps = new HashMap<>();
                    vertexProps.put("x", dumpNPC.getX());
                    vertexProps.put("y", dumpNPC.getY());
                    vertexProps.put("plane", dumpNPC.getZ());
                    vertexProps.put("actions", dumpNPC.getActions());
                    vertexProps.put("name", dumpNPC.getName());
                    vertexProps.put("npcID", dumpNPC.getNpcID());
                    OrientVertex orientVertex = graph.addVertex("class:NPC", vertexProps);
                    capture.addEdge(null, orientVertex, "class:captured");
                });
            }
            catch (Exception e){
                e.printStackTrace();
                graph.rollback();
            }
            finally {
                graph.shutdown();
                logger.info("Dump complete.");
            }
        });




       /* Document append = new Document()
                .append("x", new Document("$lte", baseX + 98).append("$gte", baseX + 3))
                .append("y", new Document("$gte", baseY + 3).append("$lte", baseY + 98))
                .append("z", plane);

        AcuityDB.getMongoClient().getDatabase("TileDB").getCollection("TileData").deleteMany(append);
        AcuityDB.getMongoClient().getDatabase("TileDB").getCollection("SEData").deleteMany(append);
        AcuityDB.getMongoClient().getDatabase("TileDB").getCollection("NPCData").deleteMany(append);

        AcuityDB.getMongoClient().getDatabase("TileDB").getCollection("Captures").insertOne(
                new Document("_id", baseX + ":" + baseY + ":" + plane)
                        .append("x", baseX)
                        .append("y", baseY)
                        .append("z", plane)
                        .append("w", 98)
                        .append("h", 98)
        );

        Npcs.streamLoaded().forEach(npc -> collectedNPCs.add(new DumpNPC(npc,
                npc.getWorldLocation().getWorldX(), npc.getWorldLocation().getWorldY(), npc.getWorldLocation().getPlane())));

        executor.execute(() -> {
            List<UpdateOneModel<Document>> collect = collectedNPCs.build().map(document -> new UpdateOneModel<Document>(
                            new Document("_id", document.getID()),
                            document.toUpdate(),
                            new UpdateOptions().upsert(true)
                    )
            ).collect(Collectors.toList());
            logger.debug("Saving {} dumped NPCs.", collect.size());
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
        });*/
    }

    public static void clear() {
        tileData.drop();
    }
}
