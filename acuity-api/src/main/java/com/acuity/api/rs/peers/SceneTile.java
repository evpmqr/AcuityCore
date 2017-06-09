package com.acuity.api.rs.peers;

import com.acuity.rs.api.RSSceneTile;
import com.google.common.base.Preconditions;
import com.sun.istack.internal.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Zachary Herridge on 6/9/2017.
 */
public class SceneTile extends Node{

    private static final Logger logger = LoggerFactory.getLogger(SceneTile.class);

    private RSSceneTile rsSceneTile;

    public SceneTile(@NotNull RSSceneTile peer) {
        super(peer);
        Preconditions.checkNotNull(peer);
        this.rsSceneTile = peer;
    }

    public RSSceneTile getRsSceneTile() {
        logger.trace("Accessing peer directly via getter.");
        return rsSceneTile;
    }
}
