package com.acuity.db.services.impl;

import com.acuity.db.AcuityDB;
import com.acuity.db.domain.edge.impl.AddedScript;
import com.acuity.db.services.DBEdgeCollectionService;

/**
 * Created by Zachary Herridge on 8/8/2017.
 */
public class ScriptAddedService extends DBEdgeCollectionService<AddedScript> {

    private static final ScriptAddedService INSTANCE = new ScriptAddedService();

    public static ScriptAddedService getInstance() {
        return INSTANCE;
    }

    public ScriptAddedService() {
        super(AcuityDB.DB_NAME, "AddedScript", AddedScript.class);
    }
}
