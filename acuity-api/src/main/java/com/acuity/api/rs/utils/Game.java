package com.acuity.api.rs.utils;

import com.acuity.api.AcuityInstance;

/**
 * Created by Zach on 6/17/2017.
 */
public class Game {

    public static final int NOT_INITALIZED = 0;
    public static final int CLIENT_LOADING = 5;
    public static final int LOGIN_SCREEN = 10;
    public static final int LOADING_SCREEN = 25;
    public static final int IN_GAME = 30;

    public static int getGameState(){
        return AcuityInstance.getClient().getGameState();
    }

}
