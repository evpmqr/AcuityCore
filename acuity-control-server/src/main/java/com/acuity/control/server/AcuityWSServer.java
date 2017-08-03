package com.acuity.control.server;

import com.acuity.control.server.sessions.Sessions;
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
        Sessions.createSession(webSocket);
    }

    @Override
    public void onClose(WebSocket webSocket, int code, String reason, boolean remote) {
        Sessions.closeSession(webSocket);
    }

    @Override
    public void onMessage(WebSocket webSocket, String message) {
        Sessions.getSession(webSocket).ifPresent(socketSession -> socketSession.message(message));
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {
        Sessions.getSession(webSocket).ifPresent(socketSession -> socketSession.error(e));
    }

    @Override
    public void onStart() {
        logger.info("Websocket server started.");
    }
}
