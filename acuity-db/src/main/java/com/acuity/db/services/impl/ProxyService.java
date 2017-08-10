package com.acuity.db.services.impl;

import com.acuity.db.AcuityDB;
import com.acuity.db.domain.vertex.impl.Proxy;
import com.acuity.db.services.DBCollectionService;

/**
 * Created by Zachary Herridge on 8/10/2017.
 */
public class ProxyService extends DBCollectionService<Proxy> {

    private static final ProxyService INSTANCE = new ProxyService();

    public static ProxyService getInstance() {
        return INSTANCE;
    }

    public ProxyService() {
        super(AcuityDB.DB_NAME, "Proxy", Proxy.class);
    }




}
