package com.acuity.db;

import com.acuity.db.services.AcuityAccountService;
import com.acuity.db.util.DBAccess;
import com.google.gson.Gson;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;

/**
 * Created by Zachary Herridge on 8/1/2017.
 */
public class AcuityDB {

    private static OrientGraphFactory controlCoreFactory;

    public static OrientGraphFactory getControlCoreFactory() {
        return controlCoreFactory;
    }

    public static boolean initMapDataDB(int poolMin, int poolMax){
        controlCoreFactory = new OrientGraphFactory("remote:acuitybotting.com/ControlCore", DBAccess.getUsername(), DBAccess.getPassword()).setupPool(poolMin, poolMax);
        return true;
    }

    public static void main(String[] args) {
        initMapDataDB(1, 10);
        Vertex zach = AcuityAccountService.getInstance().findByUsername("Zach");
        if (zach != null){
            String s1 = new Gson().toJson(zach);
            System.out.println(s1);
        }
    }
}
