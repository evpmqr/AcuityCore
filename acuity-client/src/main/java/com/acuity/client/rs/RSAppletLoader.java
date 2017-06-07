package com.acuity.client.rs;

import java.applet.Applet;
import java.io.File;

/**
 * Created by Zachary Herridge on 6/1/2017.
 */
public class RSAppletLoader {

    public static final String INJECTED_JAR_PATH = "";

    private boolean initialSetupComplete = false;
    private RSConfig rsConfig;
    private RSClassLoader classLoader;
    private Class<?> appletClass;
    private Applet applet;

    public Applet loadApplet() throws Exception {
        if (!initialSetupComplete){
            rsConfig = RSConfig.load();
            String initialClass = rsConfig.getProperty(RSConfig.INITIAL_CLASS).replace(".class", "");
            classLoader = new RSClassLoader(new File(INJECTED_JAR_PATH));
            appletClass = classLoader.loadClass(initialClass);
            initialSetupComplete = true;
        }
        applet = (Applet) appletClass.newInstance();
        return applet;
    }

    public Applet getApplet() {
        return applet;
    }

    public Class<?> getAppletClass() {
        return appletClass;
    }

    public RSClassLoader getClassLoader() {
        return classLoader;
    }
}
