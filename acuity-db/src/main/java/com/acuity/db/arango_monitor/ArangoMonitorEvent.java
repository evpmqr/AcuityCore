package com.acuity.db.arango_monitor;

/**
 * Created by Zachary Herridge on 8/3/2017.
 */
public class ArangoMonitorEvent {

    private long tick;
    private int type;
    private int database;
    private int cid;
    private int tid;
    private String document;


    @Override
    public String toString() {
        return "ArangoMonitorEvent{" +
                "tick=" + tick +
                ", type=" + type +
                ", database=" + database +
                ", cid=" + cid +
                ", tid=" + tid +
                ", vertex='" + document + '\'' +
                '}';
    }

    public long getTick() {
        return tick;
    }

    public int getType() {
        return type;
    }

    public int getDatabase() {
        return database;
    }

    public int getCid() {
        return cid;
    }

    public int getTid() {
        return tid;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }
}
