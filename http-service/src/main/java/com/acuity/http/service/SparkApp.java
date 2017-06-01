package com.acuity.http.service;

import spark.Spark;
import spark.servlet.SparkApplication;

/**
 * Created by Zach on 5/31/2017.
 */
public class SparkApp implements SparkApplication {
    @Override
    public void init() {
        setupRoutes();
    }

    public void setupRoutes(){
        Spark.get("/HelloWorld", (request, response) -> "Hello Zach");
    }

}
