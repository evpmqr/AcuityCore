package com.acuity.control.server.sessions;

import org.java_websocket.WebSocket;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Zachary Herridge on 8/3/2017.
 */
public class Sessions {

    private static Map<WebSocket, SocketSession> sessionMap = new ConcurrentHashMap<>();

    public static SocketSession createSession(WebSocket socket){
        SocketSession socketSession = new SocketSession(socket);
        sessionMap.put(socketSession.getWebSocket(), socketSession);
        socketSession.open();
        return socketSession;
    }

    public static Optional<SocketSession> closeSession(WebSocket socket){
        SocketSession remove = sessionMap.remove(socket);
        if (remove != null){
            remove.close();
        }
        return Optional.ofNullable(remove);
    }

    public static Optional<SocketSession> getSession(WebSocket socket){
        return Optional.ofNullable(sessionMap.get(socket));
    }
}
