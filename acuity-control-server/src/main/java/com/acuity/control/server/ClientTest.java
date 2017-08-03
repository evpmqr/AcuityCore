package com.acuity.control.server;

import com.acuity.db.domain.MessagePackage;
import com.google.gson.Gson;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Zachary Herridge on 8/3/2017.
 */
public class ClientTest extends WebSocketClient {

    public ClientTest(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    public ClientTest(URI serverURI) {
        super(serverURI);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("opened connection");

        MessagePackage messagePackage = new MessagePackage();
        messagePackage.getHeaders().put("AUTH", "asdsadsa");
        send(new Gson().toJson(messagePackage));
    }

    @Override
    public void onMessage(String message) {
        System.out.println("received: " + message);
    }

    @Override
    public void onFragment(Framedata fragment) {
        System.out.println("received fragment: " + new String(fragment.getPayloadData().array()));
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        // The codecodes are documented in class org.java_websocket.framing.CloseFrame
        System.out.println("Connection closed by " + (remote ? "remote peer" : "us"));
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
        // if the error is fatal then onClose will be called additionally
    }

    public static void main(String[] args) throws URISyntaxException {
        ClientTest c = new ClientTest(new URI("ws://localhost:8015"), new Draft_6455()); // more about drafts here: http://github.com/TooTallNate/Java-WebSocket/wiki/Drafts
        c.connect();
    }
}
