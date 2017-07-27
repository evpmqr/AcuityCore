package com.acuity.api.rs.utils;

import com.acuity.api.AcuityInstance;
import com.acuity.api.rs.events.impl.GameTickEvent;

/**
 * Created by Zach on 6/17/2017.
 */
public class Game {

    public static final int NOT_INITALIZED = 0;
    public static final int CLIENT_LOADING = 5;
    public static final int LOGIN_SCREEN = 10;
    public static final int LOADING_SCREEN = 25;
    public static final int IN_GAME = 30;
    public static final int CONNECTION_LOST = 40;
    public static final int CONNECTION_RECONNECTING = 45; //maybe

    public static int getGameState(){
        return AcuityInstance.getClient().getGameState();
    }

    public static long getLastGameTick(){
        return GameTickEvent.INSTANCE.getTickCounter();
    }
}
