package com.acuity.api.rs.events.impl;

import com.acuity.api.rs.events.RSEvent;

/**
 * Created by Zachary Herridge on 7/6/2017.
 */
public class GameTickEvent implements RSEvent{

    private static long tickCounter = 0;

    public GameTickEvent() {

    }

    public static void incrementTick(){
        tickCounter++;
    }

    public static long getTickCounter() {
        return tickCounter;
    }
}
