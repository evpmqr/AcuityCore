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
        if (sceneX > 104 || sceneX < 0 || sceneY > 104 || sceneY < 0 || plane < 0 || plane > 3) {
            throw new IllegalArgumentException("Coordinates outside loaded scene.");
        }
        return getCurrentScene().map(scene -> scene.getTile(sceneX, sceneY, plane)).flatMap(Function.identity());
    }
}
