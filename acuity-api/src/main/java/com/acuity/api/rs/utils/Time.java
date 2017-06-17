package com.acuity.api.rs.utils;

import java.util.function.BooleanSupplier;

/**
 * Created by Eclipseop.
 * Date: 6/17/2017.
 */
public class Time {

	public static boolean sleep(int durationMills) {
		if (durationMills <= 0) {
			return true;
		}
		try {
			Thread.sleep(durationMills);
		} catch (InterruptedException ignored) {
		}

		return true;
	}

	public static boolean sleep(int minDurationMills, int maxDurationMills) {
		return sleep(Random.nextInt(minDurationMills, maxDurationMills));
	}

	public static boolean sleepUntil(BooleanSupplier condition, long timeoutMills) {
		timeoutMills = System.currentTimeMillis() + timeoutMills;
		while (System.currentTimeMillis() < timeoutMills && !Thread.interrupted()){
			if (condition.getAsBoolean()){
				return true;
			}
			sleep(290);
		}
		return false;
	}
}
