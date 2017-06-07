package com.acuity.client;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by Zachary Herridge on 6/7/2017.
 */
public class RSClassLoader extends URLClassLoader {


    public RSClassLoader(File file) throws MalformedURLException {
        super(new URL[]{file.toURL()});
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        System.out.println("RSLoading class: " + name);
        return super.loadClass(name, resolve);
    }
}
