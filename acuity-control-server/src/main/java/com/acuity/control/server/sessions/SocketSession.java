package com.acuity.control.server.sessions;

import com.acuity.db.AcuityDB;
import com.acuity.db.domain.MessagePackage;
import com.google.gson.Gson;
import org.java_websocket.WebSocket;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Zachary Herridge on 8/3/2017.
 */
public class SocketSession {

    private WebSocket webSocket;

    public SocketSession(WebSocket webSocket) {
        this.webSocket = webSocket;
    }

    public WebSocket getWebSocket() {
        return webSocket;
    }

    public void close() {
        System.out.println("ScoketSession closed.");
    }

    public void open() {
        System.out.println("ScoketSession opened.");
    }

    public void message(String message) {
        if (message == null) return;

        MessagePackage messagePackage = new Gson().fromJson(message, MessagePackage.class);

        if (messagePackage.getBody().containsKey("UpdateAccount")){
            AcuityDB.getDB().db("_system").query("LET doc = DOCUMENT(\"AcuityUsers/433\")UPDATE doc WITH {username: \"PartyFrog" + ThreadLocalRandom.current().nextInt(1900, 1999) + "\"} IN AcuityUsers", null, null, null);
        }

        System.out.println(messagePackage);
    }

    public void error(Exception e) {
        e.printStackTrace();
    }
}
