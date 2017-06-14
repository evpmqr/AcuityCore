package com.acuity.api.applet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.applet.Applet;
import java.io.File;

/**
 * Created by Zachary Herridge on 6/1/2017.
 */
public class RSAppletLoader {

    private Logger logger = LoggerFactory.getLogger(RSAppletLoader.class);

    private boolean initialSetupComplete = false;
    private RSAppletConfig rsConfig;
    private RSClassLoader classLoader;
    private Class<?> appletClass;
    private Applet applet;
    private static RSAppletStub rsAppletStub;

    public Applet loadApplet() throws Exception {
        if (!initialSetupComplete){
            logger.info("Applet class not initiated, starting load.");
            rsConfig = RSAppletConfig.load();
            String initialClass = rsConfig.getProperty(RSAppletConfig.INITIAL_CLASS).replace(".class", "");
            classLoader = new RSClassLoader(new File(getClass().getClassLoader().getResource("Injected Gamepack.jar").getFile()));
            appletClass = classLoader.loadClass(initialClass);
            initialSetupComplete = true;
        }
        logger.info("Applet class loaded creating new instance of applet.");
        applet = (Applet) appletClass.newInstance();
        rsAppletStub = new RSAppletStub(rsConfig, applet);
        applet.setStub(rsAppletStub);
        return applet;
    }

    public Applet getApplet() {
        return applet;
    }

    public Class<?> getAppletClass() {
        return appletClass;
    }

    public RSAppletConfig getRsConfig() {
        return rsConfig;
    }

    public RSClassLoader getClassLoader() {
        return classLoader;
    }
}
