package com.acuity.api.applet.loader;

import com.acuity.api.rs.wrappers.peers.engine.GameEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

/**
 * Created by Zach on 6/17/2017.
 */
public class ClientEnvironment<T extends GameEngine> {

    private static final Logger logger = LoggerFactory.getLogger(ClientEnvironment.class);

    private final T gameEngine;

    public ClientEnvironment(T gameEngine) {
        this.gameEngine = gameEngine;
        this.gameEngine.getApplet().setSize(new Dimension(800, 600));
    }

    public T getGameEngine() {
        return gameEngine;
    }

    public void boot(ClientStub stub) {
        logger.info("Booting client stub.");
        gameEngine.getApplet().setStub(stub);
        gameEngine.getApplet().init();
        gameEngine.getApplet().start();
    }

    public void kill() {
        if (!isActive()) {
            logger.error("Game loader is inactive!");
            return;
        }
        gameEngine.getApplet().stop();
        gameEngine.getApplet().destroy();
    }


    public boolean isActive() {
        return gameEngine.getApplet().isActive();
    }
}
