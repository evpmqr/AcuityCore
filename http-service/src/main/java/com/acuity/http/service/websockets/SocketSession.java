package com.acuity.http.service.websockets;

import com.acuity.http.api.util.JsonUtil;
import com.acuity.http.api.websockets.message.Message;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.eclipse.jetty.websocket.api.Session;

import java.util.HashMap;
import java.util.Optional;

/**
 * Created by Zachary Herridge on 6/13/2017.
 */
public class SocketSession {

    private Session session;
    private EventBus eventBus = new EventBus();

    public SocketSession(Session session) {
        this.session = session;
    }

    public void socketClosed(){
        System.out.println("Socket Closed " + SocketServer.getConnections().size());
    }

    public void socketOpened(){
        System.out.println("Socket Opened");
    }

    public void socketMessage(Message message) {
        System.out.println("Sever got: " + message);
    }
}