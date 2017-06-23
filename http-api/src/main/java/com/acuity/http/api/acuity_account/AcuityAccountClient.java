package com.acuity.http.api.acuity_account;

import com.acuity.db.entities.AcuityAccount;
import com.acuity.http.api.AcuityHttpClient;
import okhttp3.HttpUrl;

import java.util.Optional;

/**
 * Created by Zachary Herridge on 6/1/2017.
 */
public class AcuityAccountClient {

    public static Optional<AcuityAccount> findCurrentAccount(){
        HttpUrl account = AcuityHttpClient.API_BASE.newBuilder()
                .addPathSegment("account")
                .build();
        return AcuityHttpClient.makeCall(account, AcuityAccount.class);
    }
}
