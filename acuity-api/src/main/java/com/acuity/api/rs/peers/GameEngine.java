package com.acuity.api.rs.peers;

import com.acuity.rs.api.RSGameEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

/**
 * Created by Zachary Herridge on 6/9/2017.
 */
public class GameEngine<T extends RSGameEngine> extends Wrapper<T> {

    private static final Logger logger = LoggerFactory.getLogger(GameEngine.class);

    public GameEngine(T peer) {
        super(peer);
    }

    public Canvas getCanvas(){
        return peer.getCanvas();
    }
}
