package com.acuity.http.api.websockets.message;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zachary Herridge on 6/13/2017.
 */
public class MessageMapBuilder {

    public static MessageMapBuilder builder(){
        return new MessageMapBuilder();
    }

    private Map<String, Object> map = new HashMap<>();

    private MessageMapBuilder() {
    }

    public MessageMapBuilder put(String key, Object value){
        map.put(key, value);
        return this;
    }

    public Message build(){
        return new Message().setBody(map);
    }
}
