package com.acuity.db.services;

import com.acuity.db.AcuityDB;
import com.arangodb.ArangoCollection;
import com.arangodb.ArangoDatabase;

/**
 * Created by Zachary Herridge on 8/4/2017.
 */
public class DBCollectionService {

    protected final String dbName;
    protected final String dbCollectionName;

    public DBCollectionService(String dbName, String dbCollectionName) {
        this.dbName = dbName;
        this.dbCollectionName = dbCollectionName;
    }


    public ArangoDatabase getDB(){
        return AcuityDB.getDB().db(dbName);
    }

    public ArangoCollection getCollection(){
        return getDB().collection(dbCollectionName);
    }

    public String getCollectionID(){
        return getCollection().getInfo().getId();
    }
}
