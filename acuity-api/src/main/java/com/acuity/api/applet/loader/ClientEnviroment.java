package com.acuity.api.applet.loader;

import com.acuity.api.rs.wrappers.engine.GameEngine;

import java.awt.*;

/**
 * Created by Zach on 6/17/2017.
 */
public class ClientEnviroment<T extends GameEngine> {

    private final T gameEngine;

    public ClientEnviroment(T gameEngine) {
        this.gameEngine = gameEngine;
        this.gameEngine.getApplet().setSize(new Dimension(800, 600));
    }

    public T getGameEngine(){
        return gameEngine;
    }

    public void boot(ClientStub stub){
        gameEngine.getApplet().setStub(stub);
        gameEngine.getApplet().init();
        gameEngine.getApplet().start();
    }

    public void kill() {
        if (!isActive()) {
            System.err.println("Game loader is inactive!");
            return;
        }
        gameEngine.getApplet().stop();
        gameEngine.getApplet().destroy();
    }


    public boolean isActive() {
        return gameEngine.getApplet().isActive();
    }
}
