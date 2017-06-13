package com.acuity.http.api.websockets.message;

import com.acuity.http.api.AcuityHttpClient;
import com.acuity.http.api.util.JsonUtil;
import com.acuity.http.api.websockets.AcuityWSClient;
import com.google.common.base.Preconditions;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Zachary Herridge on 6/13/2017.
 */
public class MessageManager {


    private AcuityWSClient client;
    private Map<Integer, MessageFuture> futureMap = new ConcurrentHashMap<>();
    
    @SuppressWarnings("unchecked")
    public void handleMessage(String messageJson){
        Message message = JsonUtil.getGSON().fromJson(messageJson, Message.class);

        Optional.ofNullable(message.getHeaders().get("responseTarget"))
                .map(o -> (int) o)
                .map(futureMap::get)
                .ifPresent(messageFuture -> messageFuture.setResponse(message));
    }
    
    int uid = 0;//Temp for example
    public MessageFuture sendExpectingResponse(Message message){
        Preconditions.checkNotNull(message);

        int id = uid++;
        MessageFuture messageFuture = futureMap.put(id, message.getFuture());

        message.putHeader("responseAddress", id);
        send(message);

        return messageFuture;
    }

    public void send(Message message){
        client.send(JsonUtil.toJSON(message
                .putHeader("ACUITY_AUTH", AcuityHttpClient.getAcuityAuth())
        ));
    }
}
