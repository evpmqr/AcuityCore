package com.acuity.db;

import com.acuity.db.services.impl.AcuityAccountService;
import com.arangodb.ArangoDB;
import com.arangodb.velocypack.module.jdk8.VPackJdk8Module;

import java.io.InputStream;

/**
 * Created by Zachary Herridge on 8/1/2017.
 */
public class AcuityDB {

    public static final String DB_NAME = "AcuityCore-Prod";

    private static ArangoDB db = null;

    public static void init(){
        InputStream in = AcuityDB.class.getClassLoader().getResourceAsStream("db.properties");
        db = new ArangoDB.Builder()
                .registerModule(new VPackJdk8Module())
                .maxConnections(8)
                .loadProperties(in)
                .build();
    }

    public static ArangoDB getDB(){
        return db;
    }


    public static void stop() {
        if (db != null) db.shutdown();
    }

    public static void main(String[] args) {
        init();
        try {
            AcuityAccountService.getInstance().registerAccount("test@gmail.com", "TestAccount", "123123");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
