package com.acuity.db.services;

import com.acuity.db.AcuityDB;
import com.arangodb.ArangoCollection;
import com.arangodb.ArangoCursor;
import com.arangodb.ArangoDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Zachary Herridge on 8/4/2017.
 */
public class DBCollectionService<T> {

    protected final String dbName;
    protected final String dbCollectionName;
    protected Class<T> type;

    public DBCollectionService(String dbName, String dbCollectionName, Class<T> type) {
        this.dbName = dbName;
        this.dbCollectionName = dbCollectionName;
        this.type = type;
    }

    public Optional<T> getByKey(String key){
        return Optional.ofNullable(getCollection().getDocument(key, type));
    }

    public Optional<T> getByID(String id){
        if (id == null) return Optional.empty();
        return Optional.ofNullable(getDB().getDocument(id, type));
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

    public String getCollectionName() {
        return getCollection().getInfo().getName();
    }

    public List<T> getByOwner(String ownerID) {
        String query = "FOR entity IN @@collection " +
                "FILTER entity.ownerID == @ownerID " +
                "RETURN entity";
        Map<String, Object> args = new HashMap<>();
        args.put("ownerID", ownerID);
        args.put("@collection", dbCollectionName);
        ArangoCursor<T> system = getDB().query(query, args, null, type);
        return system.asListRemaining();
    }

    public void insert(T entity) {
        if (entity == null) return;
        getCollection().insertDocument(entity);
    }
}
