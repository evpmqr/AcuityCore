package com.acuity.control.server.sessions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Zachary Herridge on 8/3/2017.
 */
public class Session {

    private static Logger logger = LoggerFactory.getLogger(Session.class);

    private final String sessionKey;
    private Map<String, Object> attributes = new ConcurrentHashMap<>();

    public Session(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public <T> void setAttribute(Class<T> type, T value) {
        setAttribute(type.getName(), value);
    }

    public void setAttribute(String name, Object value) {
        if(value != null) {
            this.attributes.put(name, value);
        } else {
            this.attributes.remove(name);
        }
    }

    public <T> T getAttribute(Class<T> type) {
        if(type == null) {
            throw new IllegalArgumentException("type can not be null");
        } else {
            Object value = this.getAttribute(type.getName(), null);
            return value == null?null:type.cast(value);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T getAttribute(String key, T defaultValue) {
        return (T) attributes.getOrDefault(key, defaultValue);
    }

    public String getSessionKey() {
        return sessionKey;
    }
}
