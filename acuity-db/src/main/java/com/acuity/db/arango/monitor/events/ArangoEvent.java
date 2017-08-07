package com.acuity.db.arango.monitor.events;

/**
 * Created by Zachary Herridge on 8/4/2017.
 */
public interface ArangoEvent {

    int CREATE_OR_UPDATE = 2300;
    int DELETE = 2302;

    long getTick();

    int getType();

    int getDatabase();

    String getCid();

    String getTid();

    String getDocument();

    String getCName();

}
