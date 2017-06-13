package com.acuity.http.service.websockets;

import com.acuity.http.api.util.JsonUtil;
import com.acuity.http.api.websockets.message.Message;
import com.acuity.http.service.websockets.message_handler.BotMessageHandler;
import com.acuity.http.api.websockets.message.MessageHandler;
import com.google.common.eventbus.EventBus;
import org.eclipse.jetty.websocket.api.Session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Zachary Herridge on 6/13/2017.
 */
public class SocketSession {

    private Session session;
    private EventBus eventBus = new EventBus();

    private BotMessageHandler botMessageHandler = new BotMessageHandler(this);
    private List<MessageHandler> messageHandlers = new ArrayList<>();

    public SocketSession(Session session) {
        this.session = session;

        messageHandlers.add(botMessageHandler);
    }

    public void respondTo(Message messageToRespondTo, Message message){
        messageToRespondTo.getHeader("responseAddress", Integer.class)
                .ifPresent(responseAddress -> message.putHeader("responseTarget", responseAddress));
        send(message);
    }

    public void send(Message message){
        try {
            getSession().getRemote().sendString(JsonUtil.toJSON(message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Session getSession() {
        return session;
    }

    public void socketClosed(){
        System.out.println("Socket Closed " + SocketServer.getConnections().size());
    }

    public void socketOpened(){
        System.out.println("Socket Opened");
    }

    public void socketMessage(Message message) {
        System.out.println("Sever got: " + message);
        for (MessageHandler messageHandler : messageHandlers) {
            messageHandler.handle(message);
        }
    }
}