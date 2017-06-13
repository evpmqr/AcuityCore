package com.acuity.http.api.websockets.message;

import com.acuity.http.api.util.JsonUtil;

import java.util.HashMap;

/**
 * Created by Zachary Herridge on 6/13/2017.
 */
public class Message {

    private MessageFuture future;
    private HashMap<String, Object> headers = new HashMap<>();
    private String bodyJson;


    public MessageFuture getFuture(){
        if (future == null) future = new MessageFuture();
        return future;
    }

    public Message putHeader(String key, Object value){
        headers.put(key, value);
        return this;
    }

    public Message setBodyJson(String bodyJson) {
        this.bodyJson = bodyJson;
        return this;
    }

    public Message setBody(Object object){
        this.bodyJson = JsonUtil.toJSON(object);
        return this;
    }

    public HashMap<String, Object> getHeaders() {
        return headers;
    }

    @Override
    public String toString() {
        return "Message{" +
                "headers=" + headers +
                ", bodyJson='" + bodyJson + '\'' +
                '}';
    }
}
