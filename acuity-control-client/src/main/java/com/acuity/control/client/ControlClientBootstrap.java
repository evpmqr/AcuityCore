package com.acuity.control.client;

import com.acuity.control.client.websockets.WClientEvent;
import com.acuity.db.domain.vertex.impl.MessagePackage;
import com.google.common.eventbus.Subscribe;

import java.net.URISyntaxException;

/**
 * Created by Zach on 8/5/2017.
 */
public class ControlClientBootstrap {


    public ControlClientBootstrap() {
        try {
            AcuityWSClient.getInstance().getEventBus().register(this);
            AcuityWSClient.getInstance().start("ws://localhost:8015");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void onConnect(WClientEvent.Opened opened){
        AcuityWSClient.getInstance().send(new MessagePackage(MessagePackage.Type.LOGIN)
                .putBody("username", "zgherridge@gmail.com")
                .putBody("password", "Akaliopdontnerf!)1")
                .putBody("sessionType", 1));
    }

    @Subscribe
    public void onMessage(MessagePackage messagePackage){
        if ("kill-bot".equals(messagePackage.getBody("command", null))){
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new ControlClientBootstrap();
    }

}
