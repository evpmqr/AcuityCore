package com.acuity.db.arango.monitor.events;

/**
 * Created by Zachary Herridge on 8/3/2017.
 */
public class ArangoEventImpl implements ArangoEvent{

    private long tick;
    private int type;
    private int database;
    private String cid;
    private String tid;
    private String cname;
    private String document;

    @Override
    public String toString() {
        return "ArangoEventImpl{" +
                "tick=" + tick +
                ", type=" + type +
                ", database=" + database +
                ", cid='" + cid + '\'' +
                ", tid='" + tid + '\'' +
                ", cname='" + cname + '\'' +
                ", document='" + document + '\'' +
                ", data=" + document +
                '}';
    }

    @Override
    public long getTick() {
        return tick;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public int getDatabase() {
        return database;
    }

    @Override
    public String getCid() {
        return cid;
    }

    @Override
    public String getTid() {
        return tid;
    }

    @Override
    public String getDocument() {
        return document ;
    }

    @Override
    public String getCName() {
        return cname;
    }

    public void setDocument(String document) {
        this.document = document;
    }
}
