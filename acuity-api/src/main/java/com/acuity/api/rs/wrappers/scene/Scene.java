package com.acuity.api.rs.wrappers.scene;


import com.acuity.api.annotations.ClientInvoked;
import com.acuity.api.rs.wrappers.scene.elements.SceneElement;
import com.acuity.rs.api.RSScene;
import com.acuity.rs.api.RSSceneTile;
import com.google.common.base.Preconditions;
import com.sun.istack.internal.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Optional;

/**
 * Created by Zachary Herridge on 6/9/2017.
 */
public class Scene {

    private static final Logger logger = LoggerFactory.getLogger(Scene.class);

    private RSScene rsScene;

    @ClientInvoked
    public Scene(RSScene peer) {
        this.rsScene = Preconditions.checkNotNull(peer);
    }

    public SceneElement[] getElements(){// TODO: 6/12/2017 Can this be null?
        logger.trace("Wrapping RSSceneElement[] from RSScene.");
        return Arrays.stream(rsScene.getElements())
                .map(peer -> peer != null ? new SceneElement(peer) : null)
                .toArray(SceneElement[]::new);
    }

    //getTiles in nullable
    public Optional<SceneTile[][][]> getTiles(){
        logger.trace("Wrapping RSSceneTile[][][] from RSScene.");
        RSSceneTile[][][] tiles = rsScene.getTiles();
        if (tiles == null) return Optional.empty();

        return Optional.of(Arrays.stream(tiles)
                .map(tiles1 -> Arrays.stream(tiles1)
                        .map(tiles2 -> Arrays.stream(tiles2)
                                .map(peer -> peer != null ? peer.getWrapper() : null)
                                .toArray(SceneTile[]::new)
                        ).toArray(SceneTile[][]::new)
                ).toArray(SceneTile[][][]::new));
    }

    @NotNull
    public RSScene getRsScene() {
        return rsScene;
    }
}
