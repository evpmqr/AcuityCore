package com.acuity.db.services;

import com.acuity.db.AcuityDB;
import com.arangodb.ArangoCollection;
import com.arangodb.ArangoDatabase;

import java.util.Optional;

/**
 * Created by Zachary Herridge on 8/4/2017.
 */
public class DBCollectionService<T> {

    protected final String dbName;
    protected final String dbCollectionName;
    private Class<T> type;

    public DBCollectionService(String dbName, String dbCollectionName, Class<T> type) {
        this.dbName = dbName;
        this.dbCollectionName = dbCollectionName;
        this.type = type;
    }

    public Optional<T> getByKey(String key){
        return Optional.ofNullable(getCollection().getDocument(key, type));
    }

    public T getByID(String id){
        return getDB().getDocument(id, type);
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
