package com.acuity.api;

import com.acuity.api.applet.RSAppletLoader;
import com.acuity.api.applet.RSAppletStub;
import com.acuity.api.applet.input.MouseMiddleMan;
import com.acuity.api.rs.events.GameStateChangeEvent;
import com.acuity.api.rs.wrappers.engine.Client;
import com.acuity.api.script.ScriptManager;
import com.acuity.rs.api.RSClient;
import com.google.common.base.Preconditions;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.sun.istack.internal.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.applet.Applet;

/**
 * Created by Zachary Herridge on 6/9/2017.
 */
public class AcuityInstance {

    private static final Logger logger = LoggerFactory.getLogger(AcuityInstance.class);

    private static AcuityInstance instance;

    private EventBus rsEventBus = new EventBus();// TODO: 6/14/2017 Inject this?

    private Client client;
    private Applet applet;
    private RSAppletLoader appletLoader;
    private RSAppletStub rsAppletStub;

    private MouseMiddleMan mouseMiddleMan = new MouseMiddleMan();

    private ScriptManager scriptManager;

    private AcuityInstance() throws Exception {
        logger.info("Applet loading started.");
        rsEventBus.register(this);
        appletLoader = new RSAppletLoader();
        applet = appletLoader.loadApplet();
        scriptManager = new ScriptManager();
    }

    private void load(){
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
        if (changeEvent.getPreviousGameState() == 5 && changeEvent.getGamestate() == 10){
            mouseMiddleMan.replace(client.getCanvas());
        }
    }

    public static void init() throws Exception {
        instance = new AcuityInstance();
    }

    public static void loadClient(){
        Preconditions.checkNotNull(instance, "Init the acuity instance before loading the client.").load();
    }

    public static Applet getApplet() {
        return instance.applet;
    }

    public static RSAppletLoader getAppletLoader() {
        return instance.appletLoader;
    }

    public static RSAppletStub getRsAppletStub() {
        return instance.rsAppletStub;
    }

    public static ScriptManager getScriptManager() {
        return instance.scriptManager;
    }

    @NotNull
    public static Client getClient(){
        return Preconditions.checkNotNull(instance.client, "Make sure the client is loaded before referencing it.");
    }

    public static EventBus getEventBus() {
        return instance.rsEventBus;
    }
}
