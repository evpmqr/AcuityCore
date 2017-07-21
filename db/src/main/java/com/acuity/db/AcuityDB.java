package com.acuity.db;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Properties;

/**
 * Created by Zachary Herridge on 6/12/2017.
 */
public class AcuityDB {


    private static MongoClient mongoClient;
    private static Jongo jongo;
    private static MongoCollection acuityAccountMongoCollection;


    public static void init() throws IOException {
        Properties properties = new Properties();
        try (InputStream input = AcuityDB.class.getClassLoader().getResourceAsStream("acuitydb.properties")){
            properties.load(input);
        }

        String password = properties.getProperty("dbPassword");
        MongoClientURI mongoClientURI = new MongoClientURI("mongodb://AcuityAdmin:" + password + "@testcluster-shard-00-00-efzmg.mongodb.net:27017,testcluster-shard-00-01-efzmg.mongodb.net:27017,testcluster-shard-00-02-efzmg.mongodb.net:27017/" + "admin" + "?ssl=true&replicaSet=TestCluster-shard-0&authSource=admin");
        mongoClient = new MongoClient(mongoClientURI);
        jongo = new Jongo(mongoClient.getDB("AcuityBotting-2-Prod"));
        acuityAccountMongoCollection = jongo.getCollection("Acuity-Accounts");
    }

    public static MongoCollection getAccountCollection() {
        return acuityAccountMongoCollection;
    }

    public static MongoClient getMongoClient() {
        return mongoClient;
    }

    public static boolean isActive(){
        return mongoClient != null;
    }
}
