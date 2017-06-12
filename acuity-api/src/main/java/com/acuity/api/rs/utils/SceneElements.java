package com.acuity.api.rs.utils;

import com.acuity.api.rs.wrappers.scene.SceneTile;
import com.acuity.api.rs.wrappers.scene.elements.SceneElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created by Zachary Herridge on 6/12/2017.
 */
public class SceneElements {

    private static final Logger logger = LoggerFactory.getLogger(SceneElements.class);

    public static Stream<SceneElement> streamLoaded(){
        Stream.Builder<Stream<SceneElement>> streamBuilder = Stream.builder();
        int plane = Scene.getPlane();
        for (int x = 0; x < 104; x++) {
            for (int y = 0; y < 104; y++) {
                streamBuilder.accept(streamLoaded(x, y, plane));
            }
        }
        return streamBuilder.build().flatMap(Function.identity());
    }

    public static Stream<SceneElement> streamLoaded(int sceneX, int sceneY, int plane){
        if (sceneX > 104 || sceneX < 0 || sceneY > 104 || sceneY < 0 || plane < 0 || plane > 3) {
            throw new IllegalArgumentException("Coordinates outside loaded scene,");
        }

        SceneTile[][][] sceneTiles = Scene.getTiles().orElseThrow(() -> new NullPointerException("Failed to load Scene"));

        SceneTile sceneTile = sceneTiles[plane][sceneX][sceneY];
        if (sceneTile == null){
            logger.warn("Failed to load SceneTile, returning empty Stream.");
            return Stream.empty();
        }

        return Arrays.stream(sceneTile.getMarkers());
    }
}
