package com.acuity.db;

import com.acuity.db.services.AcuityAccountService;
import com.arangodb.ArangoDB;

import java.io.InputStream;

/**
 * Created by Zachary Herridge on 8/1/2017.
 */
public class AcuityDB {

    private static ArangoDB db = null;

    public static void init(){
        InputStream in = AcuityDB.class.getClassLoader().getResourceAsStream("db.properties");
        db = new ArangoDB.Builder()
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
        System.out.println(AcuityAccountService.getInstance().checkLogin("zgherridge@gmail.com", "asdsad"));
    }
}
