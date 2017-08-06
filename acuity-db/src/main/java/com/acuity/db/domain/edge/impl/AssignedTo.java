package com.acuity.db.domain.edge.impl;

import com.acuity.db.domain.edge.Edge;

import java.time.LocalDateTime;

/**
 * Created by Zach on 8/6/2017.
 */
public class AssignedTo extends Edge {

    private LocalDateTime assignedTimeStamp;

    public AssignedTo(String rsAccountID, String botClientID) {
        super(rsAccountID, botClientID);
        this.assignedTimeStamp = LocalDateTime.now();
    }

    public AssignedTo() {
    }

    public LocalDateTime getAssignedTimeStamp() {
        return assignedTimeStamp;
    }
}
