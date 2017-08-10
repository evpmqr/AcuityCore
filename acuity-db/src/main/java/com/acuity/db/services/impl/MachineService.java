package com.acuity.db.services.impl;

import com.acuity.db.AcuityDB;
import com.acuity.db.domain.vertex.impl.Machine;
import com.acuity.db.services.DBCollectionService;

/**
 * Created by Zachary Herridge on 8/10/2017.
 */
public class MachineService extends DBCollectionService<Machine> {

    private static final MachineService INSTANCE = new MachineService();

    public static MachineService getInstance() {
        return INSTANCE;
    }

    public MachineService() {
        super(AcuityDB.DB_NAME, "Machine", Machine.class);
    }

    public String getKey(String ownerKey, String machineUsername){
        return ownerKey + ":" + machineUsername;
    }

}
