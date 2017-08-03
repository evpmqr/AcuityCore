package com.acuity.control.server.sessions;

import com.acuity.control.server.sessions.handlers.LoginHandler;
import com.acuity.control.server.sessions.handlers.MessageHandler;
import com.acuity.db.domain.vertex.impl.AcuityAccount;
import com.acuity.db.domain.vertex.impl.MessagePackage;
import com.google.gson.Gson;
import net.engio.mbassy.bus.MBassador;
import net.engio.mbassy.bus.error.IPublicationErrorHandler;
import net.engio.mbassy.bus.error.PublicationError;
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
    private MBassador<Object> eventBus = new MBassador<>(new IPublicationErrorHandler() {
        @Override
        public void handleError(PublicationError publicationError) {
            publicationError.getCause().printStackTrace();
        }
    });
    private Map<String, Object> attributes = new ConcurrentHashMap<>();

    private AcuityAccount acuityAccount;
    private MessageHandler currentHandler = new LoginHandler(this);

    public SocketSession(WebSocket webSocket) {
        this.webSocket = webSocket;
    }

    public WebSocket getWebSocket() {
        return webSocket;
    }

    public void close() {
        System.out.println("ScoketSession closed.");
    }

    public void open() {
        System.out.println("ScoketSession opened.");
    }

    public void send(MessagePackage messagePackage) {
        logger.info("Sending: " + messagePackage.toString());
        webSocket.send(gson.toJson(messagePackage));
    }

    public void message(String message) {
        if (message == null) return;
        MessagePackage messagePackage = gson.fromJson(message, MessagePackage.class);
        eventBus.post(messagePackage).asynchronously();
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @SuppressWarnings("unchecked")
    public <T> T getAttribute(String key, T defaultValue) {
        return (T) attributes.getOrDefault(key, defaultValue);
    }

    public MBassador<Object> getEventBus() {
        return eventBus;
    }

    public void setCurrentHandler(MessageHandler currentHandler) {
        this.currentHandler = currentHandler;
    }

    public void error(Exception e) {
        e.printStackTrace();
    }
}
