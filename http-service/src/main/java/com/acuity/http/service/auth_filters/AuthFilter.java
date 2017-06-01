package com.acuity.http.service.auth_filters;

import spark.Filter;
import spark.Request;
import spark.Response;

/**
 * Created by Zachary Herridge on 6/1/2017.
 */
public abstract class AuthFilter implements Filter{
    @Override
    public void handle(Request request, Response response) throws Exception {

    }
}
