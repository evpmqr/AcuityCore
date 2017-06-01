package com.acuity.http.api;

import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by Zachary Herridge on 6/1/2017.
 */
public class JSONUtil {

    private static final Gson GSON = new Gson();

    public static String toJSON(String key, Object value){
        HashMap<String, Object> stringObjectHashMap = new HashMap<>(1);
        stringObjectHashMap.put(key, value);
        return GSON.toJson(stringObjectHashMap);
    }
}
