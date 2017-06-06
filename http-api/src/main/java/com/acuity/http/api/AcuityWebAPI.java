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

    public static final AcuityWebAPI INSTANCE = new AcuityWebAPI();

    private static final String BASE_URL = "http://localhost:8080";

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    private String jwt = "NULL";

    public String getVersion(){
        return "1.0.00";
    }

    public HttpUrl getApiBase() {
        return HttpUrl.parse(BASE_URL + "/api");
    }

    public boolean login(String username, String password){
        HttpUrl login = getApiBase().newBuilder()
                .addPathSegment("login")
                .addQueryParameter("username", username)
                .addQueryParameter("password", password)
                .build();

        try {
            Response response = makeCall(login);
            try (ResponseBody body = response.body()){
                InputStream in = body.byteStream();

                HashMap hashMap = gson.fromJson(new InputStreamReader(in), HashMap.class);
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

    public Response makeCall(HttpUrl url) throws IOException {
        Request request = new Request.Builder()
                .addHeader("ACUITY_AUTH", jwt)
                .url(url)
                .build();
        return client.newCall(request).execute();
    }

    public Response makeCall(String url) throws IOException {
        Request request = new Request.Builder()
                .addHeader("ACUITY_AUTH", jwt)
                .url(url)
                .build();
        return client.newCall(request).execute();
    }

    public Gson getGson() {
        return gson;
    }

    public OkHttpClient getClient() {
        return client;
    }

    public String getJwt() {
        return jwt;
    }
}
