package com.acuity.db;

import com.acuity.http.api.acuity_account.AcuityAccount;
import com.acuity.http.api.util.JsonUtil;
import com.acuity.http.service.acuity_account.AcuityAccountService;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.bson.Document;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
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

        String username = properties.getProperty("dbUsername");
        String authDB = properties.getProperty("dbAuthLocation");
        String password = properties.getProperty("dbPassword");
        String ip = properties.getProperty("dbIP");
        String port = properties.getProperty("dbPort");

        mongoClient = new MongoClient(new ServerAddress(ip, Integer.parseInt(port)), Collections.singletonList(MongoCredential.createCredential(username, authDB, password.toCharArray())));
        jongo = new Jongo(mongoClient.getDB("AcuityBotting-2-Prod"));

        acuityAccountMongoCollection = jongo.getCollection("Acuity-Accounts");
    }

    public static MongoCollection getAccountCollection() {
        return acuityAccountMongoCollection;
    }
}
