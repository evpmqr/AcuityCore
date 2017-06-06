package com.acuity.http.api;

import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by Zach on 5/31/2017.
 */
public class AcuityWebAPI {

    private static final String BASE_URL = "http://localhost:8080";

    public static final OkHttpClient CLIENT = new OkHttpClient();
    public static final Gson GSON = new Gson();

    private static String jwt = "NULL";

    public static String getVersion(){
        return "1.0.00";
    }

    public static HttpUrl getApiBase() {
        return HttpUrl.parse(BASE_URL + "/api");
    }

    public static boolean login(String username, String password){
        HttpUrl login = getApiBase().newBuilder()
                .addPathSegment("login")
                .addQueryParameter("username", username)
                .addQueryParameter("password", password)
                .build();

        try {
            Response response = makeCall(login);
            try (ResponseBody body = response.body()){
                InputStream in = body.byteStream();


                HashMap hashMap = AcuityWebAPI.GSON.fromJson(new InputStreamReader(in), HashMap.class);
                String result = String.valueOf(hashMap.getOrDefault("result", "LOGIN_FAILED"));
                if (result.startsWith("OK|")){
                    jwt = result.substring(3);
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static Response makeCall(HttpUrl url) throws IOException {
        Request request = new Request.Builder()
                .addHeader("ACUITY_AUTH", jwt)
                .url(url)
                .build();
        return CLIENT.newCall(request).execute();
    }

    public static Response makeCall(String url) throws IOException {
        Request request = new Request.Builder()
                .addHeader("ACUITY_AUTH", jwt)
                .url(url)
                .build();
        return CLIENT.newCall(request).execute();
    }

    public static String getJwt() {
        return jwt;
    }
}
