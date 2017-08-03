package com.acuity.db.domain.vertex.impl;

import com.acuity.db.domain.vertex.Vertex;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zachary Herridge on 8/3/2017.
 */
public class MessagePackage extends Vertex {

    private Map<String, Object> headers = new HashMap<>();
    private Map<String, Object> body = new HashMap<>();

    public MessagePackage() {

    }

    public MessagePackage(int type) {
        putHeader("messageType", type);
    }

    public Map<String, Object> getHeaders() {
        return headers;
    }

    public Map<String, Object> getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "MessagePackage{" +
                "headers=" + headers +
                ", body=" + body +
                '}';
    }

    @SuppressWarnings("unchecked")
    public <T> T getBody(String key, T defaultValue) {
        return (T) body.getOrDefault(key, defaultValue);
    }

    @SuppressWarnings("unchecked")
    public <T> T getHeader(String key, T defaultValue) {
        return (T) headers.getOrDefault(key, defaultValue);
    }

    public MessagePackage putHeader(String key, Object value) {
        getHeaders().put(key, value);
        return this;
    }

    public MessagePackage putBody(String key, Object value) {
        getBody().put(key, value);
        return this;
    }

    public interface Type {
        int UNKNOWN = 0;
        int LOGIN = 1;
        int GOOD_LOGIN = 2;
        int BAD_LOGIN = 3;

    }
}
