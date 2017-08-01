package com.acuity.db.util.live_queries;

import com.orientechnologies.common.exception.OException;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.db.record.ORecordOperation;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.orientechnologies.orient.core.sql.query.OLiveQuery;
import com.orientechnologies.orient.core.sql.query.OLiveResultListener;

import java.util.List;

/**
 * Created by Zachary Herridge on 8/1/2017.
 */
public class LiveQuery implements OLiveResultListener {

    private ODatabaseDocumentTx database;
    private String query;
    private Object[] params = new Object[0];
    private String token;

    public LiveQuery(ODatabaseDocumentTx database, String query, Object... params) {
        this.database = database;
        this.query = query;
        this.params = params;
    }

    public boolean start(){
        List<ODocument> result = database.query(new OLiveQuery<ODocument>(this.query, this), params);
        this.token = result.get(0).field("token");
        return this.token != null;
    }

    public boolean stop(){
        database.command(new OCommandSQL("live unsubscribe " + token)).execute();
        return true;
    }

    @Override
    public void onLiveResult(int i, ORecordOperation oRecordOperation) throws OException {
        System.out.println("Update: " + i + " " + oRecordOperation);
    }

    @Override
    public void onError(int i) {
        System.out.println("Stopped on error: " + i);
    }

    @Override
    public void onUnsubscribe(int i) {
        System.out.println("Unsubscribed " + i);
    }
}
