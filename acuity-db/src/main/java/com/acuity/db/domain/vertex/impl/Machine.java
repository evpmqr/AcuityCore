package com.acuity.db.domain.vertex.impl;

import com.acuity.db.domain.vertex.Vertex;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Zachary Herridge on 8/9/2017.
 */
public class Machine extends Vertex{

    private String ownerID;
    private HashSet<String> ips = new HashSet<>();
    private HashMap<String, Object> properties = new HashMap<>();

    public Machine(String ownerID, String key) {
        this._key = key;
        this.ownerID = ownerID;
    }

    public Machine() {
    }

    public String getOwnerID() {
        return ownerID;
    }

    public HashSet<String> getIPs() {
        return ips;
    }

    public HashMap<String, Object> getProperties() {
        return properties;
    }
}
