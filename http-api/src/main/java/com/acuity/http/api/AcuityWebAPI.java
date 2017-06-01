package com.acuity.http.api;

import com.google.gson.Gson;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Created by Zach on 5/31/2017.
 */
public class AcuityWebAPI {

    private static final String BASE_URL = "http://localhost:8080";

    public static final OkHttpClient CLIENT = new OkHttpClient();
    public static final Gson GSON = new Gson();

    public static String getVersion(){
        return "1.0.00";
    }

    public static HttpUrl getApiBase() {
        return HttpUrl.parse(BASE_URL + "/api");
    }

    public static Response makeCall(HttpUrl url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        return CLIENT.newCall(request).execute();
    }

    public static Response makeCall(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        return CLIENT.newCall(request).execute();
    }


}
