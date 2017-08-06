package com.acuity.db.domain.vertex.impl.botclient;

import com.acuity.db.domain.vertex.Vertex;

/**
 * Created by Zach on 8/5/2017.
 */
public class BotClientConfig extends Vertex {

    private String botClientKey;
    private boolean running = true;

    public BotClientConfig(String acuityKey, String botClientKey) {
        this.botClientKey = botClientKey;
    }

    public BotClientConfig() {
    }

    public String getBotClientKey() {
        return botClientKey;
    }

    public boolean isRunning() {
        return running;
    }
}
