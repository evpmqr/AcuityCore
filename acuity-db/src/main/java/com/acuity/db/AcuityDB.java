package com.acuity.db;

import com.acuity.db.services.AcuityAccountService;
import com.acuity.db.util.DBAccess;
import com.acuity.db.util.live_queries.LiveQuery;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;

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

        OrientVertex zach = AcuityAccountService.getInstance().findByUsername("Zach");


        LiveQuery liveQuery = new LiveQuery(AcuityDB.getControlCoreFactory().getDatabase(), "live select from " + zach.getId().toString());
        liveQuery.start();

        for (int i = 0; i < 120; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        liveQuery.stop();

       /* OrientVertex eric = AcuityAccountService.getInstance().findByUsername("Eric");

        OrientGraph tx = AcuityDB.getControlCoreFactory().getTx();
        OrientVertex vertex = tx.getVertex(zach.getId());
        OrientVertex vertex2 = tx.getVertex(eric.getId());
        vertex.setProperty("testLink", vertex2);
        tx.commit();*/
    }
}
