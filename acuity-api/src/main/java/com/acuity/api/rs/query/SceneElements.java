package com.acuity.api.rs.query;

import com.acuity.api.rs.interfaces.Locatable;
import com.acuity.api.rs.utils.Scene;
import com.acuity.api.rs.wrappers.peers.scene.SceneTile;
import com.acuity.api.rs.wrappers.common.SceneElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by Zachary Herridge on 6/12/2017.
 */
public class SceneElements {

    private static final Logger logger = LoggerFactory.getLogger(SceneElements.class);

    public static Stream<SceneElement> streamLoaded(){
        Stream.Builder<SceneElement> streamBuilder = Stream.builder();
        int plane = Scene.getPlane();
        for (int x = 0; x < Scene.SIZE; x++) {
            for (int y = 0; y < Scene.SIZE; y++) {
                streamLoaded(x, y, plane).forEach(streamBuilder);
            }
        }
        return streamBuilder.build();
    }

    public static Stream<SceneElement> streamLoaded(int sceneX, int sceneY, int plane){
        if (sceneX > Scene.SIZE || sceneX < 0 || sceneY > Scene.SIZE || sceneY < 0 || plane < 0 || plane > 3) {
            throw new IllegalArgumentException("Coordinates outside loaded scene.");
        }
        return Scene.getLoaded(sceneX, sceneY, plane).map(SceneTile::streamElements).orElse(Stream.empty());
    }

    public static SceneElement getNearest(Predicate<? super SceneElement> predicate) {
        return streamLoaded().filter(predicate).sorted(Comparator.comparingInt(Locatable::distance)).findFirst().orElse(null);
    }

    public static SceneElement getNearest(final String name){
        return getNearest(sceneElement -> sceneElement.getNullSafeName().equals(name));
    }
}
