package com.acuity.control.server.sessions;

import com.acuity.control.server.sessions.handlers.LoginHandler;
import com.acuity.control.server.sessions.handlers.MessageHandler;
import com.acuity.db.domain.vertex.impl.AcuityAccount;
import com.acuity.db.domain.vertex.impl.MessagePackage;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.gson.Gson;
import org.java_websocket.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Zachary Herridge on 8/3/2017.
 */
public class SocketSession {

    private static Logger logger = LoggerFactory.getLogger(SocketSession.class);

    private static final Gson gson = new Gson();

    private WebSocket webSocket;
    private EventBus eventBus = new EventBus(this::error);
    private Map<String, Object> attributes = new ConcurrentHashMap<>();
    private AcuityAccount acuityAccount;
    private MessageHandler currentHandler = new LoginHandler(this);

    public SocketSession(WebSocket webSocket) {
        this.webSocket = webSocket;
    }

    public WebSocket getWebSocket() {
        return webSocket;
    }

    public void destroy() {
        System.out.println("ScoketSession closed.");

    }

    public void init() {
        System.out.println("ScoketSession opened.");
    }

    public void send(MessagePackage messagePackage) {
        logger.info("Sending: " + messagePackage.toString());
        webSocket.send(gson.toJson(messagePackage));
    }

    public void message(String message) {
        if (message == null) return;
        MessagePackage messagePackage = gson.fromJson(message, MessagePackage.class);
        eventBus.post(messagePackage);
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @SuppressWarnings("unchecked")
    public <T> T getAttribute(String key, T defaultValue) {
        return (T) attributes.getOrDefault(key, defaultValue);
    }

    public AcuityAccount getAcuityAccount() {
        return acuityAccount;
    }

    public void setAcuityAccount(AcuityAccount acuityAccount) {
        this.acuityAccount = acuityAccount;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public void setCurrentHandler(MessageHandler currentHandler) {
        if (this.currentHandler != null) currentHandler.destroy();
        this.currentHandler = currentHandler;
        currentHandler.init();
    }

    public void error(Throwable throwable, SubscriberExceptionContext subscriberExceptionContext) {
        throwable.printStackTrace();
    }
}
