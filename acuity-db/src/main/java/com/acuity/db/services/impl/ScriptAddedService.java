package com.acuity.db.services.impl;

import com.acuity.db.AcuityDB;
import com.acuity.db.domain.edge.impl.AddedScript;
import com.acuity.db.domain.vertex.impl.scripts.Script;
import com.acuity.db.services.DBEdgeCollectionService;

import java.util.Collections;
import java.util.List;

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

    public List<Script> getAdded(String acuityID){
        String query = "for edge in AddedScript\n" +
                "filter edge._from == @acuityID\n" +
                "let script = document(edge._to)\n" +
                "return merge(script, {\"author\" : document(script.ownerID)})";
        return getDB().query(query, Collections.singletonMap("acuityID", acuityID), null, Script.class).asListRemaining();
    }
}
