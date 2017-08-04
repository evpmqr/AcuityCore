package com.acuity.control.server.websockets;

import org.java_websocket.WebSocket;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Zachary Herridge on 8/4/2017.
 */
public class WSockets {

    private static Map<WebSocket, WSocket> socketMap = new ConcurrentHashMap<>();

    public static WSocket bindSocket(WebSocket webSocket){
        WSocket wSocket = new WSocket(webSocket);
        socketMap.put(webSocket, wSocket);
        return wSocket;
    }

    public static void remove(WSocket wSocket){
        remove(wSocket.getSocket());
    }

    public static void remove(WebSocket socket){
        socketMap.remove(socket);
    }

    public static Optional<WSocket> get(WebSocket webSocket){
        return Optional.ofNullable(socketMap.get(webSocket));
    }
}
