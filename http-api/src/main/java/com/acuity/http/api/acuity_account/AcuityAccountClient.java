package com.acuity.http.api.acuity_account;

import com.acuity.http.api.AcuityAPI;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;

/**
 * Created by Zachary Herridge on 6/1/2017.
 */
public class AcuityAccountClient {

    public Optional<AcuityAccount> findCurrentAccount(){
        HttpUrl account = AcuityAPI.getApiBase().newBuilder()
                .addPathSegment("account")
                .build();
        try {
            Response execute = AcuityAPI.makeCall(account);
            try (ResponseBody body = execute.body()){
                InputStream in = body.byteStream();
                return Optional.ofNullable(AcuityAPI.GSON.fromJson(new InputStreamReader(in), AcuityAccount.class));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    public static void main(String[] args) {
        new AcuityAccountClient().findCurrentAccount().ifPresent(System.out::print);
    }

}
