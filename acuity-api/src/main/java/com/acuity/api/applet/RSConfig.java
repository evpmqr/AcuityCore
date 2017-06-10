package com.acuity.api.applet;

import com.acuity.http.api.AcuityWebAPI;
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
public class RSConfig {

    private Logger logger = LoggerFactory.getLogger(RSConfig.class);

    private static final HttpUrl CONFIG_URL = HttpUrl.parse("http://oldschool.runescape.com/jav_config.ws");
    public static final String CODEBASE = "codebase";
    public static final String INITIAL_JAR = "initial_jar";
    public static final String INITIAL_CLASS = "initial_class";

    private final Map<String, String> properties = new HashMap<>();
    private final Map<String, String> appletProperties = new HashMap<>();

    private RSConfig() throws IOException {
        logger.info("Starting config details load from '{}'.", CONFIG_URL);
        try (Response response = AcuityWebAPI.INSTANCE.makeCall(CONFIG_URL);
             BufferedReader reader = new BufferedReader(new InputStreamReader(response.body().byteStream()))) {
            String str;
            while ((str = reader.readLine()) != null) {
                logger.trace("Got line: '{}'.", str);
                int idx = str.indexOf('=');
                if (idx == -1) {
                    continue;
                }
                String in = str.substring(0, idx);
                switch (in) {
                    case "param":
                        str = str.substring(idx + 1);
                        idx = str.indexOf('=');
                        in = str.substring(0, idx);
                        appletProperties.put(in, str.substring(idx + 1));
                        break;
                    case "msg":
                        // ignore
                        break;
                    default:
                        properties.put(in, str.substring(idx + 1));
                        break;
                }
            }
        }
        logger.debug("Finished loading config with {} general properties and {} applet properties.", properties.size(), appletProperties.size());
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

    public static RSConfig load() throws IOException {
        return new RSConfig();
    }
}
