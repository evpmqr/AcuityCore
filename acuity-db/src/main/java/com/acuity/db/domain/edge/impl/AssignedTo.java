package com.acuity.db.domain.edge.impl;

import com.acuity.db.domain.edge.Edge;

import java.time.LocalDateTime;

/**
 * Created by Zach on 8/6/2017.
 */
public class AssignedTo extends Edge {

    private String ownerID;
    private LocalDateTime assignedTimestamp;

    public AssignedTo(String ownerID, String rsAccountID, String botClientID) {
        super(rsAccountID, botClientID);
        this._key = botClientID.split("/")[1];
        this.ownerID = ownerID;
        this.assignedTimestamp = LocalDateTime.now();
    }

    public AssignedTo() {
    }

    public String getOwnerID() {
        return ownerID;
    }

    public LocalDateTime getAssignedTimestamp() {
        return assignedTimestamp;
    }
}
