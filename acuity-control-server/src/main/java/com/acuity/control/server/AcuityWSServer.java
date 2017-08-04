package com.acuity.control.server;

import com.acuity.control.server.websockets.WSocket;
import com.acuity.control.server.websockets.WSockets;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * Created by Zachary Herridge on 8/3/2017.
 */
public class AcuityWSServer extends WebSocketServer {

    private static final Logger logger = LoggerFactory.getLogger(AcuityWSServer.class);

    public AcuityWSServer(int port) throws UnknownHostException {
        super(new InetSocketAddress(port));
    }

    public AcuityWSServer(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        WSocket wSocket = WSockets.bindSocket(webSocket);
        wSocket.onOpen();
    }

    @Override
    public void onClose(WebSocket webSocket, int code, String reason, boolean remote) {
        WSockets.get(webSocket).ifPresent(wSocket -> wSocket.onClose(code, reason, remote));
        WSockets.remove(webSocket);
    }

    @Override
    public void onMessage(WebSocket webSocket, String message) {
        WSockets.get(webSocket).ifPresent(wSocket -> wSocket.onMessage(message));
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {
        WSockets.get(webSocket).ifPresent(wSocket -> wSocket.onError(e));
    }

    @Override
    public void onStart() {
        logger.info("Acuity WSServer started.");
    }
}
