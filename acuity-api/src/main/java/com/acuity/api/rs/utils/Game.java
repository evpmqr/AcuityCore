package com.acuity.api.rs.utils;

import com.acuity.api.AcuityInstance;
import com.acuity.api.rs.events.impl.GameTickEvent;

/**
 * Created by Zach on 6/17/2017.
 */
public class Game {

    public static State getGameState(){
        return State.fromValue(AcuityInstance.getClient().getGameState());
    }

    public static long getLastGameTick(){
        return GameTickEvent.INSTANCE.getTickCounter();
    }

    public enum State{
        NOT_INITIALIZED(0),
        CLIENT_LOADING(5),
        LOGIN_SCREEN(10),
        LOGGING_IN(20),
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

        public boolean isCurrent(){
            return getGameState() == this;
        }

        private static Game.State fromValue(final int value) {
            for (Game.State state : values()) {
                if (state.index == value) {
                    return state;
                }
            }
            throw new IllegalStateException("Unknown Game.State: " + value + ".");
        }

    }
}
