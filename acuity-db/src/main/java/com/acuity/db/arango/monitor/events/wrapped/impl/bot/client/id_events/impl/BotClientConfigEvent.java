package com.acuity.db.arango.monitor.events.wrapped.impl.bot.client.id_events.impl;

import com.acuity.db.arango.monitor.events.ArangoEvent;
import com.acuity.db.arango.monitor.events.wrapped.WrappedEvent;
import com.acuity.db.arango.monitor.events.wrapped.impl.bot.client.id_events.BotClientIDEvent;
import com.acuity.db.domain.vertex.impl.bot_clients.BotClientConfig;
import com.acuity.db.services.impl.BotClientService;
import com.acuity.db.util.Json;

/**
 * Created by Zachary Herridge on 8/9/2017.
 */
public class BotClientConfigEvent extends WrappedEvent implements BotClientIDEvent{

    private BotClientConfig botClientConfig;

    public BotClientConfigEvent(ArangoEvent arangoEvent) {
        super(arangoEvent);
        this.botClientConfig = Json.GSON.fromJson(getDocument(), BotClientConfig.class);
    }

    public BotClientConfig getBotClientConfig() {
        return botClientConfig;
    }

    @Override
    public String getBotClientID() {
        return BotClientService.getInstance().getCollectionName() + "/" + botClientConfig.getKey();
    }

    @Override
    public String getOwnerID() {
        return botClientConfig.getOwnerID();
    }
}
