package com.acuity.http.api.pricecheck;

import com.acuity.http.api.AcuityWebAPI;
import com.acuity.http.api.acuity_account.AcuityAccount;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Eclipseop.
 * Date: 6/1/2017.
 */
public class PriceChecker {

    private static final HttpUrl RSBUDDY_GE_URL = HttpUrl.parse("https://api.rsbuddy.com/grandExchange?a=guidePrice");

	private static final Cache<Integer, PriceLookup> cache = CacheBuilder.newBuilder()
			.expireAfterWrite(10, TimeUnit.MINUTES)
			.build();

	public static PriceLookup find(final int itemId){
		try {
			return cache.get(itemId, () -> lookup(itemId));
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static PriceLookup lookup(final int itemId) {
        HttpUrl request = RSBUDDY_GE_URL.newBuilder()
                .addQueryParameter("i", String.valueOf(itemId))
                .build();
        try {
            Response execute = AcuityWebAPI.INSTANCE.makeCall(request);
            try (ResponseBody body = execute.body()){
                InputStream in = body.byteStream();
                return AcuityWebAPI.INSTANCE.getGson().fromJson(new InputStreamReader(in), PriceLookup.class);
            }
        } catch (IOException e) {
            System.out.println("Error loading RSExchange for item " + itemId); // TODO: 6/2/2017 logger
        }

        return new PriceLookup();
	}
}
