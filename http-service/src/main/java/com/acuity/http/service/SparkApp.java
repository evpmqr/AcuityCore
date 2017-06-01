package com.acuity.http.service;

import com.acuity.http.api.AcuityAPI;
import com.acuity.http.service.auth_filters.AdminFilter;
import com.acuity.http.service.auth_filters.LoggedInFilter;
import spark.Filter;
import spark.Spark;
import spark.servlet.SparkApplication;

import static spark.Spark.*;

/**
 * Created by Zach on 5/31/2017.
 */
public class SparkApp implements SparkApplication {

    private final JSONTransformer transformer = new JSONTransformer();

    private final Filter loggedInFilter = new LoggedInFilter();
    private final Filter adminFilter = new AdminFilter();

    @Override
    public void init() {
        setupRoutes();
    }

    public void setupRoutes(){
        path("/api", () -> {
            get("/version", (request, response) -> AcuityAPI.getVersion());

            path("/authed", () -> {
                before("/*", loggedInFilter);


            });

            path("/authed/admin", () -> {
                before("/*", adminFilter);

            });
        });
    }

}
