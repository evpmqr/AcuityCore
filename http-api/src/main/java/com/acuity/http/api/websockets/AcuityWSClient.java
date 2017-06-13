package com.acuity.http.api.websockets;

import com.acuity.http.api.AcuityHttpClient;
import com.acuity.http.api.util.JsonUtil;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * Created by Zachary Herridge on 6/12/2017.
 */
public class AcuityWSClient extends WebSocketListener implements AutoCloseable{

    private static final Logger logger = LoggerFactory.getLogger(AcuityWSClient.class);

    private static final Duration PING_TIME = Duration.ofSeconds(5);

    private final OkHttpClient client = new OkHttpClient();
    private WebSocket webSocket;

    public void connect(){
        Request request = new Request.Builder()
                .url(AcuityHttpClient.WS_BASE_URL)
                .build();
        webSocket = client.newWebSocket(request, this);
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        System.out.println(text);
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        logger.info("Websocket {} opened", webSocket);
        webSocket.send(JsonUtil.toJSON("header", "SupSon"));
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        logger.info("Websocket {} closed: {}/{}", webSocket, code, reason);
        this.webSocket = null;
    }

    @Override
    public void close() throws Exception {
        if (webSocket != null){
            webSocket.close(1000, null);
        }
    }

    public static void main(String[] args) {
        new AcuityWSClient().connect();
    }
}
