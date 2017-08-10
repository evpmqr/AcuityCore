package com.acuity.db.arango.monitor.events.wrapped.impl.bot.client.id_events;

import com.acuity.db.arango.monitor.events.ArangoEvent;

/**
 * Created by Zachary Herridge on 8/9/2017.
 */
public interface BotClientIDEvent extends ArangoEvent{

    String getBotClientID();

    String getOwnerID();

}
