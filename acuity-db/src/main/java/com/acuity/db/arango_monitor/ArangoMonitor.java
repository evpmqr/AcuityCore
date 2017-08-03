package com.acuity.db.arango_monitor;

import com.google.common.eventbus.EventBus;

/**
 * Created by Zachary Herridge on 8/3/2017.
 */
public class ArangoMonitor {

    private EventBus eventBus = new EventBus();
    private ArangoMonitorStream arangoMonitor;

    public ArangoMonitor(ArangoMonitorStream arangoMonitor) {
        this.arangoMonitor = arangoMonitor;
        this.arangoMonitor.addListener(event -> eventBus.post(event));
        this.arangoMonitor.start();
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public ArangoMonitorStream getArangoMonitor() {
        return arangoMonitor;
    }
}
