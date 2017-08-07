package com.acuity.db.arango.monitor.events.wrapped.impl;

import com.acuity.db.arango.monitor.events.ArangoEvent;
import com.acuity.db.arango.monitor.events.wrapped.WrappedEvent;
import com.acuity.db.domain.vertex.impl.RSAccount;
import com.acuity.db.util.Json;

/**
 * Created by Zachary Herridge on 8/7/2017.
 */
public class RSAccountEvent extends WrappedEvent {

    private RSAccount rsAccount;

    public RSAccountEvent(ArangoEvent arangoEvent) {
        super(arangoEvent);
        this.rsAccount = Json.GSON.fromJson(arangoEvent.getDocument(), RSAccount.class);
    }

    public RSAccount getRsAccount() {
        return rsAccount;
    }
}
