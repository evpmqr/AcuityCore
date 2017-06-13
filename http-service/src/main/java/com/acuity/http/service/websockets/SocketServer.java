package com.acuity.http.service.websockets;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import com.google.common.eventbus.EventBus;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;


/**
 * Created by Zachary Herridge on 6/12/2017.
 */
@WebSocket
public class SocketServer {

    private static final Logger logger = LoggerFactory.getLogger(SocketServer.class);

    private static BiMap<Session, SocketSession> connections = Maps.synchronizedBiMap(HashBiMap.create());

    @OnWebSocketConnect
    public void onConnect(Session session) throws Exception {
        SocketSession socketSession = new SocketSession(session);
        connections.forcePut(session, socketSession);
        socketSession.socketOpened();
    }

    @OnWebSocketClose
    public void onClose(Session session, int statusCode, String reason) {
        SocketSession remove = connections.remove(session);
        if (reason != null){
            remove.socketClosed();
        }
    }

    @OnWebSocketMessage
    public void onMessage(Session user, String message) {
        Optional.ofNullable(connections.get(user))
                .orElseThrow(() -> new IllegalStateException("Message from session not in our known connections."))
                .socketMessage(message);
    }

    public static BiMap<Session, SocketSession> getConnections() {
        return connections;
    }
}
