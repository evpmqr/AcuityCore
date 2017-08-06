package com.acuity.db.arango.monitor.events.wrapped;

import com.acuity.db.arango.monitor.events.ArangoEvent;

/**
 * Created by Zachary Herridge on 8/4/2017.
 */
public class WrappedEvent implements ArangoEvent {

    protected ArangoEvent arangoEvent;

    public WrappedEvent(ArangoEvent arangoEvent) {
        this.arangoEvent = arangoEvent;
    }

    @Override
    public long getTick() {
        return arangoEvent.getTick();
    }

    @Override
    public int getType() {
        return arangoEvent.getType();
    }

    @Override
    public int getDatabase() {
        return arangoEvent.getDatabase();
    }

    @Override
    public String getCid() {
        return arangoEvent.getCid();
    }

    @Override
    public String getTid() {
        return arangoEvent.getTid();
    }

    @Override
    public String getDocument() {
        return arangoEvent.getDocument();
    }

    @Override
    public String getCName() {
        return arangoEvent.getCName();
    }

    @Override
    public String toString() {
        return "WrappedEvent{" +
                "arangoEvent=" + arangoEvent +
                '}';
    }
}
