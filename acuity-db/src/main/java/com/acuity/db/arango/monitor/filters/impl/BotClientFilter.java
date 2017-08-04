package com.acuity.db.arango.monitor.filters.impl;

import com.acuity.db.arango.monitor.ArangoMonitorEvent;
import com.acuity.db.arango.monitor.filters.ArangoMonitorFilter;
import com.acuity.db.domain.vertex.impl.AcuityAccount;
import com.acuity.db.domain.vertex.impl.BotClient;
import com.acuity.db.util.Json;

import java.util.Optional;

/**
 * Created by Zachary Herridge on 8/4/2017.
 */
public class BotClientFilter implements ArangoMonitorFilter<BotClient> {

    private AcuityAccount acuityAccount;

    public BotClientFilter(AcuityAccount acuityAccount) {
        this.acuityAccount = acuityAccount;
    }

    @Override
    public Optional<BotClient> matches(ArangoMonitorEvent event) {
        System.out.println(event.getDocument());
        BotClient botClient = Json.GSON.fromJson(event.getDocument(), BotClient.class);
        if (acuityAccount.getKey().equals(botClient.getOwnerID())){
            return Optional.of(botClient);
        }
        return Optional.empty();
    }
}
