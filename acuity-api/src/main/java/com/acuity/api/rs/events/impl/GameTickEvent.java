package com.acuity.api.rs.events.impl;

import com.acuity.api.rs.events.RSEvent;

/**
 * Created by Zachary Herridge on 7/6/2017.
 */
public class GameTickEvent implements RSEvent{

    public static final GameTickEvent INSTANCE = new GameTickEvent();

    private static long tickCounter = 0;

    public static void incrementTick(){
        tickCounter++;
    }

    public long getTickCounter() {
        return tickCounter;
    }
}
