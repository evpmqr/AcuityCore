package com.acuity.db;

import com.arangodb.ArangoDB;
import com.arangodb.entity.BaseDocument;

import java.io.InputStream;

/**
 * Created by Zachary Herridge on 8/1/2017.
 */
public class AcuityDB {


    public static void main(String[] args) {
        InputStream in = AcuityDB.class.getClassLoader().getResourceAsStream("db.properties");
        ArangoDB arangoDB = new ArangoDB.Builder()
                .maxConnections(8)
                .loadProperties(in)
                .build();

        BaseDocument document = arangoDB.db("_system").collection("AcuityUsers").getDocument("433", BaseDocument.class);

        System.out.println(document);

    }
}
