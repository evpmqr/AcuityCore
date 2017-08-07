package com.acuity.db.arango.monitor.events.wrapped.impl;

import com.acuity.db.arango.monitor.events.ArangoEvent;
import com.acuity.db.arango.monitor.events.wrapped.WrappedEvent;
import com.acuity.db.domain.vertex.impl.botclient.BotClient;
import com.acuity.db.util.Json;

/**
 * Created by Zachary Herridge on 8/4/2017.
 */
public class BotClientEvent extends WrappedEvent {

    private BotClient botClient;

    public BotClientEvent(ArangoEvent arangoEvent) {
        super(arangoEvent);
        botClient = Json.GSON.fromJson(getDocument(), BotClient.class);
    }

    public BotClient getBotClient() {
        return botClient;
    }

    @Override
    public String toString() {
        return "BotClientEvent{" +
                "arangoEvent=" + arangoEvent +
                ", botClient=" + botClient +
                '}';
    }
}
