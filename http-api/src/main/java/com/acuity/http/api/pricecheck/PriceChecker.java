package com.acuity.http.api.pricecheck;

import com.acuity.http.api.AcuityWebAPI;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Eclipseop.
 * Date: 6/1/2017.
 */
public class PriceChecker {

	private static final String RSB_URL = "https://api.rsbuddy.com/grandExchange?a=guidePrice&i=";
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
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(RSB_URL + itemId).openStream()))) {
			final PriceLookup priceLookup = AcuityWebAPI.INSTANCE.getGson().fromJson(bufferedReader.readLine(), PriceLookup.class);
			cache.put(itemId, priceLookup);
			return priceLookup;
		} catch (IOException e) {
			System.out.println("Error loading RSExchange for item " + itemId); // TODO: 6/2/2017 logger
			return new PriceLookup();
		}
	}
}
