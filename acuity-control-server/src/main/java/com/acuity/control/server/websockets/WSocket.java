package com.acuity.control.server.websockets;

import com.acuity.control.server.messages.handlers.impl.LoginHandler;
import com.acuity.control.server.sessions.Session;
import com.acuity.db.domain.vertex.impl.MessagePackage;
import com.google.common.eventbus.EventBus;
import com.google.gson.Gson;
import org.java_websocket.WebSocket;

import java.util.Optional;

/**
 * Created by Zachary Herridge on 8/4/2017.
 */
public class WSocket {

    private static Gson gson = new Gson();

    private WebSocket socket;
    private Session session;
    private EventBus eventBus = new EventBus();

    public WSocket(WebSocket socket) {
        this.socket = socket;
        eventBus.register(new LoginHandler(this));
    }

    public void send(MessagePackage messagePackage){
        socket.send(gson.toJson(messagePackage));
    }

    public void onMessage(String message){
        MessagePackage messagePackage = gson.fromJson(message, MessagePackage.class);
        if (messagePackage != null) eventBus.post(messagePackage);
    }

    public void onError(Exception e) {
        eventBus.post(new WSocketEvents.SocketError(e));
    }

    public void onClose(int code, String reason, boolean remote) {
        eventBus.post(new WSocketEvents.SocketClosed(code, reason, remote));
    }

    public void onOpen() {
        eventBus.post(new WSocketEvents.SocketOpened());
    }

    public WebSocket getSocket() {
        return socket;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Optional<Session> getSession(){
        return Optional.ofNullable(session);
    }

    public EventBus getEventBus() {
        return eventBus;
    }
}
