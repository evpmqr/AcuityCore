package com.acuity.api.applet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by Zachary Herridge on 6/7/2017.
 */
public class RSClassLoader extends URLClassLoader {

    private static final Logger logger = LoggerFactory.getLogger(RSClassLoader.class);

    public RSClassLoader(File file) throws MalformedURLException {
        super(new URL[]{file.toURL()});
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        logger.trace("Loading class '{}'", name);
        return super.loadClass(name, resolve);
    }
}
