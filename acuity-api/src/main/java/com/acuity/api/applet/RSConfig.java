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
public class RSConfig {

    private static final Logger logger = LoggerFactory.getLogger(RSConfig.class);

    public static final String INITIAL_CLASS = "initial_class";
    public static final String CODEBASE = "codebase";
    private static final String INITIAL_JAR = "initial_jar";
    private static final HttpUrl CONFIG_URL = HttpUrl.parse("http://oldschool.runescape.com/jav_config.ws");

    private final Map<String, String> properties = new HashMap<>();
    private final Map<String, String> appletProperties = new HashMap<>();

    private RSConfig() throws IOException {
        logger.info("Starting config details load from '{}'.", CONFIG_URL);
        try (Response response = AcuityHttpClient.makeCall(CONFIG_URL, false); BufferedReader reader = new BufferedReader(new InputStreamReader(response.body().byteStream()))) {
            String str;
            while ((str = reader.readLine()) != null) {
                logger.trace("Got line: '{}'.", str);
                int idx = str.indexOf('=');
                if (idx == -1) {
                    continue;
                }
                String in = str.substring(0, idx);
                switch (in) {
                    case "msg":
                        // ignore
                        break;
                    case "param":
                        str = str.substring(idx + 1);
                        idx = str.indexOf('=');
                        in = str.substring(0, idx);
                        appletProperties.put(in, str.substring(idx + 1));
                        break;
                    default:
                        properties.put(in, str.substring(idx + 1));
                        break;
                }
            }
        }
        logger.debug("Finished loading config with {} general properties and {} applet properties.", properties.size(), appletProperties.size());
    }

    public static RSConfig load() throws IOException {
        return new RSConfig();
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
