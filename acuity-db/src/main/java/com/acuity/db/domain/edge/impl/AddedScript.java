package com.acuity.db.domain.edge.impl;

import com.acuity.db.domain.edge.Edge;

import java.time.LocalDateTime;

/**
 * Created by Zachary Herridge on 8/7/2017.
 */
public class AddedScript extends Edge{

    private LocalDateTime addedTimeStamp = LocalDateTime.now();

    public AddedScript(String acuityID, String scriptID) {
        super(acuityID, scriptID);
        this._key = acuityID.split("/")[1] + ":" + scriptID.split("/")[1];
    }

    public AddedScript() {
    }

    public LocalDateTime getAddedTimeStamp() {
        return addedTimeStamp;
    }
}
