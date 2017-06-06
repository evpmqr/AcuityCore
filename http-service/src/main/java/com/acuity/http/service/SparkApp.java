package com.acuity.http.service;

import com.acuity.http.api.AcuityWebAPI;
import com.acuity.http.api.util.JsonUtil;
import com.acuity.http.service.acuity_account.AcuityAccountService;
import com.acuity.http.service.auth_filters.AdminFilter;
import com.acuity.http.service.auth_filters.LoggedInFilter;
import com.acuity.http.service.transformers.ObjectToJSONTransformer;
import spark.Filter;
import spark.servlet.SparkApplication;

import static spark.Spark.*;

/**
 * Created by Zach on 5/31/2017.
 */
public class SparkApp implements SparkApplication {

    private final ObjectToJSONTransformer objectToJSONTransformer = new ObjectToJSONTransformer();

    private final Filter loggedInFilter = new LoggedInFilter();
    private final Filter adminFilter = new AdminFilter();

    private AcuityAccountService acuityAccountService = new AcuityAccountService();

    @Override
    public void init() {
        setupRoutes();
    }

    public void setupRoutes(){
        path("/api", () -> {
            get("/version", (request, response) -> JsonUtil.toJSON("version", AcuityWebAPI.INSTANCE.getVersion()));

            get("/account", acuityAccountService::findCurrentAccount, objectToJSONTransformer);
            get("/login", (request, response) -> JsonUtil.toJSON("result", acuityAccountService.login(request, response)));

            path("/authed", () -> {
                before("/*", loggedInFilter);

                path("/admin", () -> {
                    before("/*", adminFilter);

                });
            });
        });
    }
}
