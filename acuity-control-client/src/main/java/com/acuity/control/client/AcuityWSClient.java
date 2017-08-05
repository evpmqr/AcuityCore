package com.acuity.control.client;

import com.acuity.control.client.websockets.WClient;
import com.acuity.db.domain.vertex.impl.MessagePackage;
import com.acuity.db.util.Json;
import com.google.common.eventbus.EventBus;
import org.java_websocket.drafts.Draft_6455;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;

/**
 * Created by Zach on 8/5/2017.
 */
public class AcuityWSClient {

    private static final Logger logger = LoggerFactory.getLogger(AcuityWSClient.class);

    private static final AcuityWSClient INSTANCE = new AcuityWSClient();

    public static AcuityWSClient getInstance() {
        return INSTANCE;
    }

    private WClient wClient;

    public void start() throws URISyntaxException {
        wClient = new WClient("ws://localhost:8015", new Draft_6455());
        wClient.connect();
    }

    public void stop(){
        wClient.close();
    }

    public void send(MessagePackage messagePackage){
        wClient.send(Json.GSON.toJson(messagePackage));
    }

    public EventBus getEventBus(){
        return wClient.getEventBus();
    }
}
