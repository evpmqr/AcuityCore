package com.acuity.api.rs.events;

/**
 * Created by Zach on 6/14/2017.
 */
public class GameStateChangeEvent implements RSEvent {

    private static int lastGameState = 0;

    private int previousGameState;
    private int gamestate;

    public GameStateChangeEvent(int gamestate) {
        this.gamestate = gamestate;
        previousGameState = lastGameState;
        lastGameState = gamestate;
    }

    public int getGamestate() {
        return gamestate;
    }

    public int getPreviousGameState() {
        return previousGameState;
    }

    @Override
    public String toString() {
        return "GameStateChangeEvent{" +
                "previousGameState=" + previousGameState +
                ", gamestate=" + gamestate +
                '}';
    }
}
