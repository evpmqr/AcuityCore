package com.acuity.control.server;

import com.acuity.db.arango.monitor.events.ArangoEventDispatcher;
import com.google.common.eventbus.EventBus;

/**
 * Created by Zachary Herridge on 8/4/2017.
 */
public class Events {

    private static ArangoEventDispatcher arangoEventDispatcher = new ArangoEventDispatcher();

    public static void start(){
        arangoEventDispatcher.start();
    }

    public static void stop(){
        arangoEventDispatcher.stop();
    }

    public static EventBus getDBEventBus() {
        return arangoEventDispatcher.getEventBus();
    }
}
