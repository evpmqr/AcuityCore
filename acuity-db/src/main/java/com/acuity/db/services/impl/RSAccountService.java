package com.acuity.db.services.impl;

import com.acuity.db.AcuityDB;
import com.acuity.db.domain.vertex.impl.RSAccount;
import com.acuity.db.services.DBCollectionService;

/**
 * Created by Zach on 8/6/2017.
 */
public class RSAccountService extends DBCollectionService<RSAccount> {

    private static final RSAccountService INSTANCE = new RSAccountService();

    public static RSAccountService getInstance() {
        return INSTANCE;
    }

    public RSAccountService() {
        super(AcuityDB.DB_NAME, "RSAccount", RSAccount.class);
    }




}
