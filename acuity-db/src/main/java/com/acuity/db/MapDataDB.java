package com.acuity.db;

import com.acuity.db.util.DBAccess;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;

/**
 * Created by Zachary Herridge on 8/1/2017.
 */
public class MapDataDB {

    private static OrientGraphFactory mapDataFactory;

    public static OrientGraphFactory getMapDataFactory() {
        return mapDataFactory;
    }

    public static boolean initMapDataDB(int poolMin, int poolMax){
        mapDataFactory = new OrientGraphFactory("remote:acuitybotting.com/MapData", DBAccess.getUsername(), DBAccess.getPassword()).setupPool(poolMin, poolMax);
        return true;
    }

}
