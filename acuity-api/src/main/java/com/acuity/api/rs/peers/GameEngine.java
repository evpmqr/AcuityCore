package com.acuity.api.rs.peers;

import com.acuity.rs.api.RSGameEngine;
import com.google.common.base.Preconditions;
import com.sun.istack.internal.NotNull;

/**
 * Created by Zachary Herridge on 6/9/2017.
 */
public class GameEngine {

    private RSGameEngine rsGameEngine;

    public GameEngine(@NotNull RSGameEngine peer) {
        Preconditions.checkNotNull(peer);
        this.rsGameEngine = peer;
    }
}
