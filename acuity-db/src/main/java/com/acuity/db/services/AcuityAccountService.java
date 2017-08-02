package com.acuity.db.services;

/**
 * Created by Zachary Herridge on 8/1/2017.
 */
public class AcuityAccountService {

    private static final AcuityAccountService INSTANCE = new AcuityAccountService();

    public static AcuityAccountService getInstance() {
        return INSTANCE;
    }

}
