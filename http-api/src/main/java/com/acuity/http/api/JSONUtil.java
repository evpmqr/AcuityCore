package com.acuity.http.api;

import com.google.gson.Gson;

import java.util.Collections;
import java.util.HashMap;

/**
 * Created by Zachary Herridge on 6/1/2017.
 */
public class JSONUtil {

    private static final Gson GSON = new Gson();

    public static String toJSON(String key, Object value){
        return GSON.toJson(Collections.singletonMap(key, value));
    }
}
