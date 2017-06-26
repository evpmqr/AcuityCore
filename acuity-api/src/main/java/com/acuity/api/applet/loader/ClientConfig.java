package com.acuity.api.applet.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;
import java.util.function.Consumer;

public class ClientConfig implements Consumer<String> {

    private static final Logger logger = LoggerFactory.getLogger(ClientConfig.class);

    private static String OLDSCHOOL_JAV_CONFIG = "http://oldschool.runescape.com/jav_config.ws";
    private final Properties properties, parameters;

    public ClientConfig(int world) {
        properties = parameters = new Properties();
        String url = OLDSCHOOL_JAV_CONFIG;
        if (world > 0 && world < 95) {
            logger.info("Attempting to load into world {}.", 300 + world);
            url = url.replace("oldschool", "oldschool" + world);
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
            reader.lines().forEach(this);
        } catch (IOException e) {
            logger.error("Error during config reading", e);
        }
        logger.info("Loaded RSConfig with {} properties and {} parameters.", properties.size(), parameters.size());
    }

    public String getDownloadPath() {
        return properties.getProperty("codebase", "http://" + "oldschool1" + ".runescape.com/k=3/") + properties.getProperty("initial_jar");
    }

    public String getParameter(String key) {
        return parameters.getProperty(key);
    }

    public String getParameter(String key, String defaultValue) {
        return parameters.getProperty(key, defaultValue);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    @Override
    public void accept(String line) {
        if (line.startsWith("param=")) {
            String s = line.substring("param=".length());
            String[] parts = s.split("=", 2);
            if (parts.length == 2) {
                parameters.put(parts[0], parts[1]);
            }
        } else if (!line.startsWith("msg=")) {
            String[] parts = line.split("=", 2);
            if (parts.length == 2) {
                properties.put(parts[0], parts[1]);
            }
        }
    }
}