package com.acuity.http.service.websockets.message_handler;

import com.acuity.http.api.websockets.message.Message;
import com.acuity.http.api.websockets.message.MessageHandler;
import com.acuity.http.api.websockets.message.MessageMapBuilder;
import com.acuity.http.service.websockets.SocketSession;

import java.util.Optional;

/**
 * Created by Zachary Herridge on 6/13/2017.
 */
public class BotMessageHandler implements MessageHandler {

    private SocketSession socketSession;

    public BotMessageHandler(SocketSession socketSession) {
        this.socketSession = socketSession;
    }

    @Override
    public void handle(Message message) {
        Optional<String> command = message.getHeader("command", String.class);
        if (command.isPresent()){
            switch (command.get()){
                case "init":
                    socketSession.respondTo(message, MessageMapBuilder.builder()
                            .put("botID", "tempID")
                            .build());
                    break;
            }
        }
    }
}
