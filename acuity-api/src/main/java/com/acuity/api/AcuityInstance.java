package com.acuity.api;

import com.acuity.api.applet.AppletManager;
import com.acuity.api.rs.events.GameStateChangeEvent;
import com.acuity.api.rs.wrappers.engine.Client;
import com.acuity.api.script.ScriptManager;
import com.google.common.base.Preconditions;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.sun.istack.internal.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;

/**
 * Created by Zachary Herridge on 6/9/2017.
 */
public class AcuityInstance {

    private static final Logger logger = LoggerFactory.getLogger(AcuityInstance.class);

    private static AcuityInstance instance;

    private AppletManager appletManager;
    private ScriptManager scriptManager;

    private AcuityInstance() throws Exception {
        logger.info("Applet loading started.");
        scriptManager = new ScriptManager();

        appletManager = new AppletManager();
    }

    public static void init() throws Exception {
        instance = new AcuityInstance();
        getAppletManager().load();
    }

    public static void boot(){
        getAppletManager().boot();
    }

    public static AppletManager getAppletManager() {
        return Preconditions.checkNotNull(instance, "Init the AcuityInstance before referencing it.").appletManager;
    }

    public static ScriptManager getScriptManager() {
        return Preconditions.checkNotNull(instance, "Init the AcuityInstance before referencing it.").scriptManager;
    }

    @NotNull
    public static Client getClient(){
        return getAppletManager().getClient();
    }
}
