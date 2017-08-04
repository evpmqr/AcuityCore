package com.acuity.control.server.messages.handlers.impl;

import com.acuity.control.server.messages.handlers.MessageHandler;
import com.acuity.control.server.websockets.WSocket;
import com.acuity.db.domain.vertex.impl.MessagePackage;

/**
 * Created by Zachary Herridge on 8/3/2017.
 */
public class BotClientHandler extends MessageHandler {

    public BotClientHandler(WSocket wSocket) {
        super(wSocket);
    }

    @Override
    public void handle(MessagePackage messagePackage) {

    }
}
