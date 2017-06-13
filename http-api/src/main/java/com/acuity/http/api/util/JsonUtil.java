package com.acuity.http.api.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Collections;

/**
 * Created by Zachary Herridge on 6/1/2017.
 */
public class JsonUtil {

    private static final Gson GSON = new Gson();

    public static String toJSON(String key, Object value){
        return GSON.toJson(Collections.singletonMap(key, value));
    }

    public static String toJSON(Object value){
        return GSON.toJson(value);
    }

    public static Gson getGSON() {
        return GSON;
    }
}
