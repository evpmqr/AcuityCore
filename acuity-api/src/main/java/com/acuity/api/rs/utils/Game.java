package com.acuity.api.rs.utils;

import com.acuity.api.AcuityInstance;
import com.acuity.api.rs.events.impl.GameTickEvent;

/**
 * Created by Zach on 6/17/2017.
 */
public class Game {

    public enum State{
        NOT_INITALIZED(0),
        CLIENT_LOADING(5),
        LOGIN_SCREEN(10),
        LOADING_SCREEN(25),
        IN_GAME(30),
        CONNECTION_LOST(40),
        CONNECTION_RECONNECTING(45);

        private final int index;

        State(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }

    public static int getGameState(){
        return AcuityInstance.getClient().getGameState();
    }

    public static long getLastGameTick(){
        return GameTickEvent.INSTANCE.getTickCounter();
    }
}
