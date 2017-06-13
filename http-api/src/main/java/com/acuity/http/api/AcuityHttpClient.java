package com.acuity.http.api;

import com.acuity.http.api.acuity_account.AcuityAccount;
import com.acuity.http.api.acuity_account.AcuityAccountClient;
import com.acuity.http.api.util.JsonUtil;
import okhttp3.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Optional;

/**
 * Created by Zachary Herridge on 6/13/2017.
 */
public class AcuityHttpClient {

    public static final String WS_BASE_URL = "ws://localhost:8080/api/ws";
    public static final String HTTP_BASE_URL = "http://localhost:8080";
    public static final HttpUrl API_BASE = HttpUrl.parse(HTTP_BASE_URL + "/api");

    private static final OkHttpClient client = new OkHttpClient();

    private static String jwtToken = null;

    public static Response makeCall(HttpUrl url) throws IOException {
        return makeCall(url, true);
    }

    public static Response makeCall(HttpUrl url, boolean addAcuityAuth) throws IOException {
        Request.Builder request = new Request.Builder().url(url);
        if (addAcuityAuth) request.addHeader("ACUITY_AUTH", getAcuityAuth());
        return client.newCall(request.build()).execute();
    }

    @SuppressWarnings("unchecked")
    public static boolean login(String username, String password){
        HttpUrl login = AcuityHttpClient.API_BASE.newBuilder()
                .addPathSegment("login")
                .addQueryParameter("username", username)
                .addQueryParameter("password", password)
                .build();
        try {
            Response response = AcuityHttpClient.makeCall(login);
            try (ResponseBody body = response.body()){
                InputStream in = body.byteStream();

                HashMap hashMap = JsonUtil.getGSON().fromJson(new InputStreamReader(in), HashMap.class);
                String result = String.valueOf(hashMap.getOrDefault("result", "LOGIN_FAILED"));
                if (result.startsWith("LOGIN_SUCCESS:")){
                    jwtToken = result.substring("LOGIN_SUCCESS:".length());
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getAcuityAuth() {
        return Optional.ofNullable(jwtToken).orElse("NO_AUTH");
    }

    public static OkHttpClient getClient() {
        return client;
    }

    public static void main(String[] args) {
        System.out.println(AcuityHttpClient.login("zgherridge@gmail.com", "password123"));

        try {
            Response response = makeCall(API_BASE.newBuilder().addPathSegment("authed").addPathSegment("test").build());
            if (response.code() == 401){
                return;
            }

            try (ResponseBody body = response.body()) {
                InputStream in = body.byteStream();

                HashMap hashMap = JsonUtil.getGSON().fromJson(new InputStreamReader(in), HashMap.class);
                System.out.println(hashMap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
