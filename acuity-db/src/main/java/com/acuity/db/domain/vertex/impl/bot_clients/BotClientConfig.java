package com.acuity.db.domain.vertex.impl.bot_clients;

import com.acuity.db.domain.vertex.Vertex;
import com.acuity.db.services.impl.BotClientService;

/**
 * Created by Zach on 8/5/2017.
 */
public class BotClientConfig extends Vertex {

    private String acuityID;
    private String botClientID;
    private boolean running = true;

    public BotClientConfig(String acuityID, String botClientKey) {
        this._key = botClientKey;
        this.acuityID =  acuityID;
        this.botClientID = BotClientService.getInstance().getCollectionName() + "/" + botClientKey;
    }

    public BotClientConfig() {
    }

    public String getBotClientID() {
        return botClientID;
    }

    public boolean isRunning() {
        return running;
    }
}
