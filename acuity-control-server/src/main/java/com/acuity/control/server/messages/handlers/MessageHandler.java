package com.acuity.control.server.messages.handlers;

import com.acuity.control.server.websockets.WSocket;
import com.acuity.db.domain.vertex.impl.MessagePackage;
import com.google.common.eventbus.Subscribe;

/**
 * Created by Zachary Herridge on 8/3/2017.
 */
public abstract class MessageHandler {

    private WSocket socket;

    public MessageHandler(WSocket wSocket) {
        this.socket = socket;
    }

    @Subscribe
    public abstract void handle(MessagePackage messagePackage);

    public WSocket getSocket() {
        return socket;
    }

    public void init(){
        socket.getEventBus().register(this);
    }

    public void destroy() {
        socket.getEventBus().unregister(this);
    }
}
