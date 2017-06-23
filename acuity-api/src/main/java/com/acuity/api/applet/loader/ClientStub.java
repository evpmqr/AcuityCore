package com.acuity.api.applet.loader;

import javax.imageio.ImageIO;
import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AppletStub;
import java.applet.AudioClip;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

/**
 * Represents the clients {@link AppletStub}
 */
public class ClientStub implements AppletStub {

    private final ClientConfig cfg;

    public ClientStub(ClientConfig cfg) {
        this.cfg = cfg;
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public URL getDocumentBase() {
        try {
            return new URL(cfg.getDownloadPath());
        } catch (MalformedURLException e) {
            return null;
        }
    }

    @Override
    public URL getCodeBase() {
        try {
            return new URL(cfg.getDownloadPath());
        } catch (MalformedURLException e) {
            return null;
        }
    }

    @Override
    public String getParameter(String name) {
        return cfg.getParameter(name);
    }

    @Override
    public AppletContext getAppletContext() {
        return new AppletContext() {
            private final HashMap<String, InputStream> streams = new HashMap<>();
            private Applet applet;

            @Override
            public AudioClip getAudioClip(URL url) {
                return Applet.newAudioClip(url);
            }

            @Override
            public Image getImage(URL url) {
                try {
                    return ImageIO.read(url);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public Applet getApplet(String name) {
                return applet;
            }

            @Override
            public Enumeration<Applet> getApplets() {
                final Vector<Applet> applets = new Vector<>();
                applets.add(applet);

                return applets.elements();
            }

            @Override
            public void showDocument(URL url) {

            }

            @Override
            public void showDocument(URL url, String target) {

            }

            @Override
            public void showStatus(String status) {

            }

            @Override
            public void setStream(String key, InputStream stream) throws IOException {
                if (streams.containsKey(key)) {
                    streams.remove(key);
                }
                streams.put(key, stream);
            }

            @Override
            public InputStream getStream(String key) {
                return streams.get(key);
            }

            @Override
            public Iterator<String> getStreamKeys() {
                return streams.keySet().iterator();
            }

            public void setApplet(final Applet applet) {
                this.applet = applet;
            }
        };
    }

    @Override
    public void appletResize(int width, int height) {

    }
}
