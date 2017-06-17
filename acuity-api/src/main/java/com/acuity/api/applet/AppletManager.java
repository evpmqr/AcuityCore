package com.acuity.api.applet;

import com.acuity.api.AcuityInstance;
import com.acuity.api.Events;
import com.acuity.api.applet.input.KeyboardMiddleMan;
import com.acuity.api.applet.input.MouseMiddleMan;
import com.acuity.api.applet.loader.RSAppletLoader;
import com.acuity.api.applet.loader.RSAppletStub;
import com.acuity.api.rs.events.GameStateChangeEvent;
import com.acuity.api.rs.utils.Game;
import com.acuity.api.rs.wrappers.engine.Client;
import com.acuity.rs.api.RSClient;
import com.google.common.base.Preconditions;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.applet.Applet;

/**
 * Created by Zach on 6/17/2017.
 */
public class AppletManager {

    private static final Logger logger = LoggerFactory.getLogger(AppletManager.class);

    private Client client;
    private Applet applet;
    private RSAppletLoader appletLoader;
    private RSAppletStub rsAppletStub;

    private MouseMiddleMan mouseMiddleMan = new MouseMiddleMan();
    private KeyboardMiddleMan keyboardMiddleMan = new KeyboardMiddleMan();


    public AppletManager() throws Exception {
        Events.getRsEventBus().register(this);
        appletLoader = new RSAppletLoader();
        applet = appletLoader.loadApplet();
    }

    public void load(){
        logger.info("RSClient loading started.");
        rsAppletStub = new RSAppletStub(appletLoader.getRsConfig(), applet);
        applet.setStub(rsAppletStub);

        /*
          Temporary fix for mac / linux is fails to load
          due to size of 0 applet.
         */
        applet.setSize(800, 600);

        logger.info("Booting applet.");
        applet.init();
        applet.start();
        client = ((RSClient) applet).getWrapper();
        logger.debug("RSClient loading finished.");
    }

    @Subscribe
    public void gameStateChanged(GameStateChangeEvent changeEvent){
        if (changeEvent.getPreviousGameState() == Game.CLIENT_LOADING && changeEvent.getGamestate() == Game.LOGIN_SCREEN){
            mouseMiddleMan.replace(client.getCanvas());
            keyboardMiddleMan.replace(client.getCanvas());
        }
    }

    public Client getClient() {
        return Preconditions.checkNotNull(client, "Load the RS client before referencing it.");
    }

    public Applet getApplet() {
        return applet;
    }

    public RSAppletLoader getAppletLoader() {
        return appletLoader;
    }

    public RSAppletStub getRsAppletStub() {
        return rsAppletStub;
    }

    public MouseMiddleMan getMouseMiddleMan() {
        return mouseMiddleMan;
    }

    public KeyboardMiddleMan getKeyboardMiddleMan() {
        return keyboardMiddleMan;
    }
}
