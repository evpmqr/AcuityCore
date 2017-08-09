package com.acuity.db.arango.monitor.events.wrapped.impl.bot.client.id_events.impl;

import com.acuity.db.arango.monitor.events.ArangoEvent;
import com.acuity.db.arango.monitor.events.wrapped.WrappedEvent;
import com.acuity.db.arango.monitor.events.wrapped.impl.bot.client.id_events.BotClientIDEvent;
import com.acuity.db.domain.vertex.impl.bot_clients.BotClient;
import com.acuity.db.util.Json;

/**
 * Created by Zachary Herridge on 8/4/2017.
 */
public class BotClientEvent extends WrappedEvent implements BotClientIDEvent{

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

    @Override
    public String getBotClientID() {
        return botClient.getID();
    }

    @Override
    public String getOwnerID() {
        return botClient.getOwnerID();
    }
}
