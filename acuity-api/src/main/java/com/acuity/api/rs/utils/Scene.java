package com.acuity.api.rs.utils;

import com.acuity.api.AcuityInstance;
import com.acuity.api.rs.wrappers.peers.scene.elements.impl.SceneElement;
import com.acuity.api.rs.wrappers.peers.scene.SceneTile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.function.Function;

/**
 * Created by Zachary Herridge on 6/9/2017.
 */
public class Scene {

    private static final Logger logger = LoggerFactory.getLogger(Scene.class);

    public static final int SIZE = 104;

    public static int getPlane(){
        return AcuityInstance.getClient().getPlane();
    }

    public static byte[][][] getRenderRules(){
        return AcuityInstance.getClient().getSceneRenderRules();
    }

    public static int[][][] getTileHeights() {
        return AcuityInstance.getClient().getTileHeights();
    }

    public static int getBaseX(){
        return AcuityInstance.getClient().getBaseSceneX();
    }

    public static int getBaseY(){
        return AcuityInstance.getClient().getBaseSceneY();
    }

    public static Optional<SceneElement[]> getElements(){
        return AcuityInstance.getClient().getScene()
                .map(com.acuity.api.rs.wrappers.peers.scene.Scene::getElements);
    }

    public static Optional<com.acuity.api.rs.wrappers.peers.scene.Scene> getCurrentScene(){
        return AcuityInstance.getClient().getScene();
    }

    public static Optional<SceneTile> getLoaded(int sceneX, int sceneY, int plane){
        if (sceneX > SIZE || sceneX < 0 || sceneY > SIZE || sceneY < 0 || plane < 0 || plane > 3) {
            throw new IllegalArgumentException("Coordinates outside loaded scene.");
        }
        return getCurrentScene().map(scene -> scene.getTile(sceneX, sceneY, plane)).flatMap(Function.identity());
    }

    public static Optional<Integer> getGroundHeight(int x, int y) {
        int x1 = x >> 7;
        int y1 = y >> 7;
        if (x1 < 0 || x1 > SIZE || y1 < 0 || y1 > SIZE) {
            return Optional.empty();
        }
        byte[][][] rules = Scene.getRenderRules();
        if (rules == null) {
            return Optional.empty();
        }
        int[][][] heights = Scene.getTileHeights();
        if (heights == null) {
            return Optional.empty();
        }
        int plane = Scene.getPlane();
        if (plane < 3 && (rules[1][x1][y1] & 0x2) == 2) {
            plane++;
        }
        int x2 = x & 0x7F;
        int y2 = y & 0x7F;
        int h1 = heights[plane][x1][y1] * (Projection.TILE_PIXEL_SIZE - x2) + heights[plane][x1 + 1][y1] * x2 >> 7;
        int h2 = heights[plane][x1][y1 + 1] * (Projection.TILE_PIXEL_SIZE - x2) + heights[plane][x1 + 1][y1 + 1] * x2 >> 7;
        return Optional.of(h1 * (Projection.TILE_PIXEL_SIZE - y2) + h2 * y2 >> 7);
    }
}
