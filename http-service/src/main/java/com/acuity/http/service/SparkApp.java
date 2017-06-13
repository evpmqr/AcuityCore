package com.acuity.http.service;

import ch.qos.logback.classic.Level;
import com.acuity.http.api.util.JsonUtil;
import com.acuity.http.service.acuity_account.AcuityAccountService;
import com.acuity.http.service.auth_filters.AdminFilter;
import com.acuity.http.service.auth_filters.LoggedInFilter;
import com.acuity.http.service.transformers.ObjectToJSONTransformer;
import com.acuity.http.service.websockets.SocketServer;

import org.slf4j.LoggerFactory;
import spark.Filter;
import spark.servlet.SparkApplication;
import ch.qos.logback.classic.Logger;

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
        Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        logger.setLevel(Level.INFO);

        port(8080);

        webSocket("/api/ws", SocketServer.class);

        path("/api", () -> {
            get("/version", (request, response) -> JsonUtil.toJSON("version", "1.0.01"));

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

    public static void main(String[] args) {
        new SparkApp().init();
    }
}
