package com.acuity.api.applet;

import com.acuity.http.api.AcuityHttpClient;
import okhttp3.HttpUrl;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zachary Herridge on 6/1/2017.
 */
public class RSAppletConfig {

    private static final Logger logger = LoggerFactory.getLogger(RSAppletConfig.class);

    public static final String INITIAL_CLASS = "initial_class";
    public static final String CODEBASE = "codebase";
    private static final String INITIAL_JAR = "initial_jar";
    private static final HttpUrl CONFIG_URL = HttpUrl.parse("http://oldschool.runescape.com/jav_config.ws");

    private final Map<String, String> properties = new HashMap<>();
    private final Map<String, String> appletProperties = new HashMap<>();

    private RSAppletConfig() throws IOException {
        logger.info("Starting config details load from '{}'.", CONFIG_URL);
        try (Response response = AcuityHttpClient.makeCall(CONFIG_URL, false); BufferedReader reader = new BufferedReader(new InputStreamReader(response.body().byteStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                logger.trace("Got line: '{}'.", line);
                if (!line.contains("=")) continue;
                String[] split = line.split("=");
                switch (split[0]) {
                    case "msg":
                        // ignore
                        break;
                    case "param":
                        appletProperties.put(split[0], split[1]);
                        break;
                    default:
                        properties.put(split[0], split[1]);
                        break;
                }
            }
        }
        logger.debug("Finished loading config with {} general properties and {} applet properties.", properties.size(), appletProperties.size());
    }

    public static RSAppletConfig load() throws IOException {
        return new RSAppletConfig();
    }

    public String getProperty(String name) {
        return properties.get(name);
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public String getAppletProperty(String name) {
        return appletProperties.get(name);
    }

    public Map<String, String> getAppletProperties() {
        return appletProperties;
    }
}
