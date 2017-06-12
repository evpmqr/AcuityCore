package com.acuity.http.service;

import org.eclipse.jetty.websocket.api.*;
import org.eclipse.jetty.websocket.api.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




/**
 * Created by Zachary Herridge on 6/12/2017.
 */
@WebSocket
public class SparkWS {

    private static final Logger logger = LoggerFactory.getLogger(SparkWS.class);

    @OnWebSocketConnect
    public void onConnect(Session user) throws Exception {
        logger.info("New session {}", user);

    }
}
