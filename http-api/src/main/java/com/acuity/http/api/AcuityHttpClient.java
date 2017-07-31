package com.acuity.http.api;

import com.acuity.http.api.acuity_account.AcuityAccountClient;
import com.acuity.http.api.util.JsonUtil;
import okhttp3.*;

import java.io.IOException;
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


    public static <T> Optional<T> makeCall(HttpUrl url, Class<T> tClass) {
        return makeCall(url, tClass, true);
    }

    public static <T> Optional<T> makeCall(HttpUrl url, Class<T> mapJsonTo, boolean addAcuityAuth) {
        try {
            Response response = AcuityHttpClient.makeCall(url, addAcuityAuth);
            if (response.code() == 401) return Optional.empty();
            try (ResponseBody body = response.body()) {
                return Optional.of(JsonUtil.getGSON().fromJson(new InputStreamReader(body.byteStream()), mapJsonTo));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static Response makeCall(HttpUrl url) throws IOException {
        return makeCall(url, true);
    }

    public static Response makeCall(HttpUrl url, boolean addAcuityAuth) throws IOException {
        Request.Builder request = new Request.Builder().url(url);
        if (addAcuityAuth) request.addHeader("ACUITY_AUTH", getAcuityAuth());
        return client.newCall(request.build()).execute();
    }

    @SuppressWarnings("unchecked")
    public static boolean login(String username, String password) {
        HttpUrl login = AcuityHttpClient.API_BASE.newBuilder()
                .addPathSegment("login")
                .addQueryParameter("username", username)
                .addQueryParameter("password", password)
                .build();

        return makeCall(login, HashMap.class)
                .map(hashMap -> {
                    String result = String.valueOf(hashMap.getOrDefault("result", "LOGIN_FAILED"));
                    if (result.startsWith("LOGIN_SUCCESS:")) {
                        jwtToken = result.substring("LOGIN_SUCCESS:".length());
                        return true;
                    }
                    return false;
                })
                .orElse(false);
    }

    public static String getAcuityAuth() {
        return Optional.ofNullable(jwtToken).orElse("NO_AUTH");
    }

    public static OkHttpClient getClient() {
        return client;
    }

    public static void main(String[] args) {
        AcuityHttpClient.login("zgherridge@gmail.com", "password123");
        System.out.println(AcuityAccountClient.findCurrentAccount());
        System.out.println(AcuityHttpClient.makeCall(API_BASE.newBuilder().addPathSegment("authed").addPathSegment("test").build(), HashMap.class));
    }
}
