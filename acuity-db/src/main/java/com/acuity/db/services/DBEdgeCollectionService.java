package com.acuity.db.services;

import com.arangodb.ArangoCursor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zachary Herridge on 8/7/2017.
 */
public class DBEdgeCollectionService<T> extends DBCollectionService<T> {

    public DBEdgeCollectionService(String dbName, String dbCollectionName, Class<T> type) {
        super(dbName, dbCollectionName, type);
    }

    public void removeByToID(String toID){
        String query = "FOR entity IN @@collection " +
                "FILTER entity._to == @toID " +
                "REMOVE entity in @@collection";
        Map<String, Object> args = new HashMap<>();
        args.put("toID", toID);
        args.put("@collection", dbCollectionName);
        getDB().query(query, args, null, type);
    }

    public void removeByFromID(String fromID){
        String query = "FOR entity IN @@collection " +
                "FILTER entity._from == @fromID " +
                "REMOVE entity in @@collection";
        Map<String, Object> args = new HashMap<>();
        args.put("fromID", fromID);
        args.put("@collection", dbCollectionName);
        getDB().query(query, args, null, type);
    }

    public List<T> get(String fromID, String toID){
        if (fromID == null) fromID = "";
        if (toID == null) toID = "";
        String query = "FOR entity IN @@collection " +
                "FILTER (@fromID == '' || entity._from == @fromID)  && (@toID == '' || entity._to == @toID) " +
                "RETURN entity";
        Map<String, Object> args = new HashMap<>();
        args.put("fromID", fromID);
        args.put("toID", toID);
        args.put("@collection", dbCollectionName);
        ArangoCursor<T> system = getDB().query(query, args, null, type);
        return system.asListRemaining();
    }

    public List<T> getByFromID(String fromID){
        String query = "FOR entity IN @@collection " +
                "FILTER entity._from == @fromID " +
                "RETURN entity";
        Map<String, Object> args = new HashMap<>();
        args.put("fromID", fromID);
        args.put("@collection", dbCollectionName);
        ArangoCursor<T> system = getDB().query(query, args, null, type);
        return system.asListRemaining();
    }

    public List<T> getByToID(String toID){
        String query = "FOR entity IN @@collection " +
                "FILTER entity._to == @toID " +
                "RETURN entity";
        Map<String, Object> args = new HashMap<>();
        args.put("toID", toID);
        args.put("@collection", dbCollectionName);
        ArangoCursor<T> system = getDB().query(query, args, null, type);
        return system.asListRemaining();
    }
}
