package com.acuity.control.server.sessions.handlers;

import com.acuity.control.server.sessions.SocketSession;
import com.acuity.db.domain.vertex.impl.MessagePackage;
import com.google.common.eventbus.Subscribe;

/**
 * Created by Zachary Herridge on 8/3/2017.
 */
public abstract class MessageHandler {

    private SocketSession socketSession;

    public MessageHandler(SocketSession socketSession) {
        this.socketSession = socketSession;
    }

    @Subscribe
    public abstract void handle(MessagePackage messagePackage);

    public SocketSession getSocket() {
        return socketSession;
    }

    public void init(){
        socketSession.getEventBus().register(this);
    }

    public void destroy() {
        socketSession.getEventBus().unregister(this);
    }
}
