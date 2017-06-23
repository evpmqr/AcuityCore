package com.acuity.http.api.websockets;

import com.acuity.http.api.AcuityHttpClient;
import com.acuity.http.api.util.JsonUtil;
import com.acuity.http.api.websockets.message.Message;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by Zachary Herridge on 6/12/2017.
 */
public class AWSClient extends WebSocketListener implements AutoCloseable{

    private static final Logger logger = LoggerFactory.getLogger(AWSClient.class);

    private final OkHttpClient client = new OkHttpClient();
    private WebSocket webSocket;
    private ClientMessageManager clientMessageManager;

    private void connect(){
        Request request = new Request.Builder().url(AcuityHttpClient.WS_BASE_URL).build();
        webSocket = client.newWebSocket(request, this);
        clientMessageManager = new ClientMessageManager(this);
    }

    WebSocket getWebSocket() {
        return webSocket;
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        if (clientMessageManager != null) clientMessageManager.handleMessage(JsonUtil.getGSON().fromJson(text, Message.class));
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        logger.info("Websocket {} now open.", webSocket);
        clientMessageManager.send(new Message().putHeader("command", "sup").setBody("asdasdas"));
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        logger.info("Websocket {} now closed.", webSocket, code, reason);
        this.webSocket = null;
    }

    @Override
    public void close() throws Exception {
        if (webSocket != null){
            webSocket.close(Math.toIntExact(TimeUnit.SECONDS.toMillis(1)), null);
        }
    }

    public static void main(String[] args) {
        new AWSClient().connect();
    }
}
