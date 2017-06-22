package com.acuity.http.api.websockets;

import com.acuity.http.api.AcuityHttpClient;
import com.acuity.http.api.util.JsonUtil;
import com.acuity.http.api.websockets.message.Message;
import com.acuity.http.api.websockets.message.MessageFuture;
import com.google.common.base.Preconditions;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Zachary Herridge on 6/13/2017.
 */
public class ClientMessageManager {

    private AWSClient client;
    private Map<Integer, MessageFuture> futureMap = new ConcurrentHashMap<>();

    public ClientMessageManager(AWSClient client) {
        this.client = client;
    }

    @SuppressWarnings("unchecked")
    public void handleMessage(Message message){
        Optional.ofNullable(message.getHeaders().get("responseTarget"))
                .map(o -> (int) o)
                .map(futureMap::get)
                .ifPresent(messageFuture -> messageFuture.setResponse(message));
    }
    
    int uid = 0;// TODO: 6/13/2017 Temp for example
    public MessageFuture sendWithFurute(Message message){
        Preconditions.checkNotNull(message);

        int id = uid++;
        MessageFuture messageFuture = futureMap.put(id, message.getFuture());

        message.putHeader("responseAddress", id);
        send(message);

        return messageFuture;
    }

    public void send(Message message){
        Preconditions.checkNotNull(message);
        client.getWebSocket().send(JsonUtil.toJSON(message
                .putHeader("ACUITY_AUTH", AcuityHttpClient.getAcuityAuth())
        ));
    }
}
