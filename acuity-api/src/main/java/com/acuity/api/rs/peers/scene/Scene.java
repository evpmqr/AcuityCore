package com.acuity.api.rs.peers.scene;


import com.acuity.api.rs.peers.Wrapper;
import com.acuity.rs.api.RSScene;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Created by Zachary Herridge on 6/9/2017.
 */
public class Scene<T extends RSScene> extends Wrapper<T> {

    private static final Logger logger = LoggerFactory.getLogger(Scene.class);

    public Scene(T peer) {
        super(peer);
    }

    public SceneElement[] getElements(){
        logger.trace("Wrapping SceneElement[] from RSScene.");
        return Arrays.stream(peer.getElements())
                .map(peer -> peer != null ? new SceneElement(peer) : null)
                .toArray(SceneElement[]::new);
    }

    public SceneTile[][][] getTiles(){
        logger.trace("Wrapping SceneTile[][][] from RSScene.");
        return Arrays.stream(peer.getTiles())
                .map(tile1 -> Arrays.stream(tile1)
                        .map(tile2 -> Arrays.stream(tile2)
                                .map(tile3 -> tile3 != null ? new SceneTile(tile3) : null)
                                .toArray(SceneTile[]::new)
                        ).toArray(SceneTile[][]::new)
                ).toArray(SceneTile[][][]::new);
    }
}
