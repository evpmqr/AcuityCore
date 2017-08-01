package com.acuity.db.services;

import com.acuity.db.AcuityDB;
import com.orientechnologies.orient.core.metadata.sequence.OSequence;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;

/**
 * Created by Zachary Herridge on 8/1/2017.
 */
public class AcuityAccountService {

    private static final AcuityAccountService INSTANCE = new AcuityAccountService();

    public static AcuityAccountService getInstance() {
        return INSTANCE;
    }

    public OrientVertex findByUsername(String username){
        for (Vertex vertex : AcuityDB.getControlCoreFactory().getNoTx().query().has("username", username).vertices()) {
            return (OrientVertex) vertex;
        }
        return null;
    }

    public void addAccount(String username, String passwordHash){
        OrientGraph tx = AcuityDB.getControlCoreFactory().getTx();
        try {
            tx.addVertex("class:AcuityAccount", "username", username, "passwordHash", passwordHash, "userID", getIDSequence().next());
            tx.commit();
        }
        catch (Exception e){
            tx.rollback();
        }
    }

    public OSequence getIDSequence(){
        return AcuityDB.getControlCoreFactory().getDatabase().getMetadata().getSequenceLibrary().getSequence("acuityAccountIDSeq");
    }
}
