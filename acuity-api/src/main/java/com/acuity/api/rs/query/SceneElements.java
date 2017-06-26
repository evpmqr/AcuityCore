package com.acuity.api.rs.query;

import com.acuity.api.AcuityInstance;
import com.acuity.api.rs.utils.Scene;
import com.acuity.api.rs.wrappers.scene.SceneTile;
import com.acuity.api.rs.wrappers.scene.elements.ISceneElement;
import com.acuity.api.rs.wrappers.scene.elements.SceneElement;
import com.acuity.rs.api.RSScene;
import com.acuity.rs.api.RSSceneTile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created by Zachary Herridge on 6/12/2017.
 */
public class SceneElements {

    private static final Logger logger = LoggerFactory.getLogger(SceneElements.class);

    public static Stream<ISceneElement> streamLoaded(){
        Stream.Builder<ISceneElement> streamBuilder = Stream.builder();
        int plane = Scene.getPlane();
        for (int x = 0; x < 104; x++) {
            for (int y = 0; y < 104; y++) {
                streamLoaded(x, y, plane).forEach(streamBuilder);
            }
        }
        return streamBuilder.build();
    }

    public static Stream<ISceneElement> streamLoaded(int sceneX, int sceneY, int plane){
        if (sceneX > 104 || sceneX < 0 || sceneY > 104 || sceneY < 0 || plane < 0 || plane > 3) {
            throw new IllegalArgumentException("Coordinates outside loaded scene.");
        }
        RSScene sceneGraph = AcuityInstance.getClient().getRsClient().getSceneGraph();
        if (sceneGraph != null){
            RSSceneTile[][][] tiles = sceneGraph.getTiles();
            if (tiles != null){
                RSSceneTile rsSceneTile = tiles[plane][sceneX][sceneY];
                if (rsSceneTile != null){
                    return rsSceneTile.getWrapper().streamElements();
                }
            }
        }
        return Stream.empty();
    }
}
