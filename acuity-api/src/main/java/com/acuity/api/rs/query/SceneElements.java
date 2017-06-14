package com.acuity.api.rs.query;

import com.acuity.api.rs.utils.Scene;
import com.acuity.api.rs.wrappers.scene.SceneTile;
import com.acuity.api.rs.wrappers.scene.elements.SceneElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Optional;
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
                streamBuilder.add(streamLoaded(x, y, plane));
            }
        }
        return streamBuilder.build().flatMap(Function.identity());
    }

    public static Stream<SceneElement> streamLoaded(int sceneX, int sceneY, int plane){
        if (sceneX > 104 || sceneX < 0 || sceneY > 104 || sceneY < 0 || plane < 0 || plane > 3) {
            throw new IllegalArgumentException("Coordinates outside loaded scene.");
        }

        Optional<Stream<SceneElement>> sceneElements = Scene.getTiles()
                .map(sceneTiles -> sceneTiles[plane][sceneX][sceneY])
                .map(SceneTile::getElements)
                .map(Arrays::stream);

        if (!sceneElements.isPresent()){
            logger.warn("Failed to load SceneElements at {}, {}, {}. Returning an empty Stream.", sceneX, sceneY, plane);
            return Stream.empty();
        }

        return sceneElements.get();
    }
}
