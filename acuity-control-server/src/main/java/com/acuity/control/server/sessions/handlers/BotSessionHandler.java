package com.acuity.control.server.sessions.handlers;

import com.acuity.control.server.sessions.SocketSession;
import com.acuity.db.domain.vertex.impl.MessagePackage;

/**
 * Created by Zachary Herridge on 8/3/2017.
 */
public class BotSessionHandler extends MessageHandler{

    public BotSessionHandler(SocketSession socketSession) {
        super(socketSession);
    }

    @Override
    public void handle(MessagePackage messagePackage) {

    }
}
