package com.acuity.http.service;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * Created by Zachary Herridge on 6/1/2017.
 */
public class JSONTransformer implements ResponseTransformer {
    private final Gson gson = new Gson();

    @Override
    public String render(Object model) throws Exception
    {
        return gson.toJson(model);
    }
}