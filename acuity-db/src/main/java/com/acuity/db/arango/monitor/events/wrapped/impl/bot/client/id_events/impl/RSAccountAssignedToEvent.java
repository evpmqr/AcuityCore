package com.acuity.db.arango.monitor.events.wrapped.impl.bot.client.id_events.impl;

import com.acuity.db.arango.monitor.events.ArangoEvent;
import com.acuity.db.arango.monitor.events.wrapped.WrappedEvent;
import com.acuity.db.arango.monitor.events.wrapped.impl.bot.client.id_events.BotClientIDEvent;
import com.acuity.db.domain.edge.impl.AssignedTo;
import com.acuity.db.services.impl.BotClientService;
import com.acuity.db.util.Json;

/**
 * Created by Zach on 8/6/2017.
 */
public class RSAccountAssignedToEvent extends WrappedEvent implements BotClientIDEvent{

    private AssignedTo assignedTo;

    public RSAccountAssignedToEvent(ArangoEvent arangoEvent) {
        super(arangoEvent);
        this.assignedTo = Json.GSON.fromJson(arangoEvent.getDocument(), AssignedTo.class);
    }

    public AssignedTo getEdge() {
        return assignedTo;
    }

    @Override
    public String toString() {
        return "RSAccountAssignedToEvent{" +
                "assignedTo=" + assignedTo +
                '}';
    }

    @Override
    public String getBotClientID() {
        return BotClientService.getInstance().getCollectionName() + "/" + assignedTo.getKey();
    }

    @Override
    public String getOwnerID() {
        return assignedTo.getOwnerID();
    }
}
