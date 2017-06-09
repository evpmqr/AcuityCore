package com.acuity.api;

import com.acuity.api.applet.RSAppletLoader;
import com.acuity.api.applet.RSStub;
import com.acuity.api.rs.peers.Client;
import com.acuity.rs.api.RSClient;
import com.sun.istack.internal.NotNull;

import java.applet.Applet;

/**
 * Created by Zachary Herridge on 6/9/2017.
 */
public class RSInstance {

    private static Client client;

    private static Applet applet;
    private static RSAppletLoader appletLoader;
    private static RSStub rsStub;

    public static void init() throws Exception {
        appletLoader = new RSAppletLoader();
        applet = appletLoader.loadApplet();
    }

    public static void loadClient(){
        rsStub = new RSStub(appletLoader.getRsConfig(), applet);
        applet.setStub(rsStub);
        applet.init();
        applet.start();
        client = new Client((RSClient) applet);
    }

    public static Applet getApplet() {
        return applet;
    }

    public static RSAppletLoader getAppletLoader() {
        return appletLoader;
    }

    public static RSStub getRsStub() {
        return rsStub;
    }

    @NotNull
    public static Client getClient(){
        return client;
    }
}
