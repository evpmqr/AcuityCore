package com.acuity.api.meta.tile_dumper;

import com.acuity.api.AcuityInstance;
import com.acuity.api.rs.query.Npcs;
import com.acuity.api.rs.query.SceneElements;
import com.acuity.api.rs.utils.Scene;
import com.acuity.api.rs.wrappers.common.locations.WorldLocation;
import com.acuity.rs.api.RSCollisionData;
import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;
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
    private static Executor executor;

    static {
        executor = Executors.newSingleThreadExecutor();
    }

    public static void execute() {
        Stream.Builder<DumpTile> collectedTiles = Stream.builder();
        Stream.Builder<DumpSE> collectedSEs = Stream.builder();
        Stream.Builder<DumpNPC> collectedNPCs = Stream.builder();

        int baseX = Scene.getBaseX();
        int baseY = Scene.getBaseY();
        int plane = Scene.getPlane();
        RSCollisionData rsCollisionData = AcuityInstance.getClient().getRsClient().getCollisionMaps()[plane];

        if (rsCollisionData != null) {
            int[][] flags = rsCollisionData.getFlags();
            for (int x = 3; x < flags.length; x++) {
                int[] tiles = flags[x];
                for (int y = 3; y < tiles.length; y++) {
                    if (x <= 98 && y <= 98) {
                        DumpTile dumpTile = new DumpTile(x + baseX, y + baseY, plane, tiles[y]);
                        if (dumpTile.getFlag() != 0) {
                            collectedTiles.add(dumpTile);
                        }

                        SceneElements.streamLoaded(x, y, plane)
                                .forEach(sceneElement -> collectedSEs.add(new DumpSE(sceneElement, dumpTile.getX(), dumpTile.getY(), dumpTile.getPlane())));
                    }
                }
            }
        }

        Npcs.streamLoaded().forEach(npc -> {
            WorldLocation worldLocation = npc.getWorldLocation();
            if (worldLocation.getPlane() == plane &&
                    worldLocation.getWorldX() >= (baseX + 3) && worldLocation.getWorldX() <= (baseX + 98) &&
                    worldLocation.getWorldY() >= (baseY + 3) && worldLocation.getWorldY() <= (baseY + 98)) {
                collectedNPCs.add(new DumpNPC(npc, worldLocation.getWorldX(), worldLocation.getWorldY(), worldLocation.getPlane()));
            }
        });

        executor.execute(() -> {
            OrientGraph graph = new OrientGraph("remote:acuitybotting.com/MapData", "root", "");
            logger.info("Starting tile dump, graph opened.");
            try {
                Object deleteTiles = graph.command(new OCommandSQL("delete vertex from Tile where plane = ? and x >= ? and x <= ? and y >= ? and y <= ?")).execute(plane, baseX + 3, baseX + 98, baseY + 3, baseY + 98);
                Object deleteNpcs = graph.command(new OCommandSQL("delete vertex from NPC where plane = ? and x >= ? and x <= ? and y >= ? and y <= ?")).execute(plane, baseX + 3, baseX + 98, baseY + 3, baseY + 98);
                Object deleteSEs = graph.command(new OCommandSQL("delete vertex from SceneElement where plane = ? and x >= ? and x <= ? and y >= ? and y <= ?")).execute(plane, baseX + 3, baseX + 98, baseY + 3, baseY + 98);

                logger.debug("Deleted {} Tiles.", deleteTiles);
                logger.debug("Deleted {} Npcs.", deleteNpcs);
                logger.debug("Deleted {} SceneElements.", deleteSEs);

                OrientVertex capture = graph.addVertex("class:Capture", "lowerX", baseX + 3, "lowerY", baseY + 3, "upperX", baseX + 98, "upperY", baseY + 98, "plane", plane, "timestamp", System.currentTimeMillis());

                collectedTiles.build().forEach(dumpTile -> {
                    Map<String, Object> vertexProps = new HashMap<>();
                    vertexProps.put("x", dumpTile.getX());
                    vertexProps.put("y", dumpTile.getY());
                    vertexProps.put("plane", dumpTile.getPlane());
                    vertexProps.put("flag", dumpTile.getFlag());
                    OrientVertex orientVertex = graph.addVertex("class:Tile", vertexProps);
                    capture.addEdge(null, orientVertex, "Captured");
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
                    vertexProps.put("flag", dumpSE.getFlag());
                    vertexProps.put("uid", dumpSE.getUid());
                    OrientVertex orientVertex = graph.addVertex("class:SceneElement", vertexProps);
                    capture.addEdge(null, orientVertex, "Captured");

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
                    capture.addEdge(null, orientVertex, "Captured");
                });
            } catch (Exception e) {
                logger.error("Error while dumping tiles, rolling back graph.", e);
                graph.rollback();
            } finally {
                graph.shutdown();
                logger.info("Tile dump completed, graph closed.");
            }
        });
    }
}
