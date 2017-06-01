package com.acuity.client;

import java.applet.Applet;
import java.io.IOException;

/**
 * Created by Zachary Herridge on 6/1/2017.
 */
public class RSAppletLoader {

    public static Applet load() throws IOException {
        RSConfig rsConfig = RSConfig.load();
        String initialClass = rsConfig.getProperty(RSConfig.INITIAL_CLASS).replace(".class", "");


        return null;
    }
}
