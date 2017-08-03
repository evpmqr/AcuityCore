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
}
