package com.acuity.client.rs;

import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AppletStub;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Zachary Herridge on 6/1/2017.
 */
public class RSStub implements AppletStub {

    private final RSConfig rsConfig;
    private final Applet applet;

    public RSStub(RSConfig rsConfig, Applet applet) {
        this.rsConfig = rsConfig;
        this.applet = applet;
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public URL getDocumentBase() {
        return getCodeBase();
    }

    @Override
    public URL getCodeBase() {
        try {
            return new URL(rsConfig.getProperty(RSConfig.CODEBASE));
        } catch (MalformedURLException ex) {
            return null;
        }
    }

    @Override
    public String getParameter(String name) {
        return rsConfig.getAppletProperty(name);
    }

    @Override
    public AppletContext getAppletContext() {
        return null;
    }

    @Override
    public void appletResize(int width, int height) {

    }
}
