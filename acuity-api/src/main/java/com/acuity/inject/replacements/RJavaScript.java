package com.acuity.inject.replacements;

import com.acuity.api.annotations.ClientInvoked;
import netscape.javascript.JSObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.applet.Applet;

/**
 * Created by Zachary Herridge on 7/3/2017.
 */
public class RJavaScript {

    private static final Logger logger = LoggerFactory.getLogger(RJavaScript.class);

    @ClientInvoked
    public static JSObject getWindow(Applet applet){//Should always throw an error
        JSObject window = JSObject.getWindow(applet);
        logger.warn("RS obtained a JS window.");
        return window;
    }

}
