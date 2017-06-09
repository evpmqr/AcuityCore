package com.acuity.api.rs.peers.scene;

import com.acuity.api.rs.peers.Node;
import com.acuity.api.rs.peers.mobile.Npc;
import com.acuity.api.rs.peers.mobile.Player;
import com.acuity.rs.api.RSPlayer;
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
public class SceneTile extends Node {

    private static final Logger logger = LoggerFactory.getLogger(SceneTile.class);

    private RSSceneTile rsSceneTile;

    public SceneTile(@NotNull RSSceneTile peer) {
        super(peer);
        Preconditions.checkNotNull(peer);
        this.rsSceneTile = peer;
    }

    public SceneElement[] getSceneElements(){
        logger.trace("Wrapping RSSceneElement[] from RSSceneTile.");
        return Arrays.stream(rsSceneTile.getMarkers())
                .map(peer -> peer != null ? new SceneElement(peer) : null)
                .toArray(SceneElement[]::new);
    }

    public RSSceneTile getRsSceneTile() {
        logger.trace("Accessing peer directly via getter.");
        return rsSceneTile;
    }
}
