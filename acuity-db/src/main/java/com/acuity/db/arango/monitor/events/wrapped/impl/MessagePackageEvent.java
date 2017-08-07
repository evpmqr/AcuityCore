package com.acuity.db.arango.monitor.events.wrapped.impl;

import com.acuity.db.arango.monitor.events.ArangoEvent;
import com.acuity.db.arango.monitor.events.wrapped.WrappedEvent;
import com.acuity.db.domain.vertex.impl.MessagePackage;
import com.acuity.db.util.Json;

/**
 * Created by Zachary Herridge on 8/4/2017.
 */
public class MessagePackageEvent extends WrappedEvent {

    private MessagePackage messagePackage;

    public MessagePackageEvent(ArangoEvent arangoEvent) {
        super(arangoEvent);
        messagePackage = Json.GSON.fromJson(getDocument(), MessagePackage.class);
    }

    public MessagePackage getMessagePackage() {
        return messagePackage;
    }
}
