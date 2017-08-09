package com.acuity.client;

import com.acuity.control.client.AcuityWSClient;
import com.acuity.control.client.websockets.WClientEvent;
import com.acuity.db.domain.vertex.impl.MessagePackage;
import com.google.common.eventbus.Subscribe;

/**
 * Created by Zachary Herridge on 8/9/2017.
 */
public class WSClient {

    private static WSClient INSTANCE = new WSClient();

    public static WSClient getInstance() {
        return INSTANCE;
    }

    public WSClient() {
        AcuityWSClient.getInstance().getEventBus().register(this);
    }

    public void start() throws Exception {
        AcuityWSClient.getInstance().start("ws://localhost:8015");
    }

    public void stop(){
        AcuityWSClient.getInstance().stop();
    }

    @Subscribe
    public void onConnect(WClientEvent.Opened opened){
        AcuityWSClient.getInstance().send(new MessagePackage(MessagePackage.Type.LOGIN)
                .putBody("username", "test@gmail.com")
                .putBody("password", "123123")
                .putBody("sessionType", 1));
    }

    @Subscribe
    public void onMessage(MessagePackage messagePackage){
        if ("kill-bot".equals(messagePackage.getBody("command", null))){
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        try {
            getInstance().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
