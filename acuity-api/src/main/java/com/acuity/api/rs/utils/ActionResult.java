package com.acuity.api.rs.utils;

import java.util.function.BooleanSupplier;

/**
 * Created by Eclipseop.
 * Date: 6/17/2017.
 */
public enum ActionResult {
	SUCCESS,
	FAILURE,
	NO_ACTION;

	public boolean isSuccess() {
		return equals(SUCCESS);
	}

	public boolean isFailure() {
		return equals(FAILURE);
	}

	public ActionResult sleep(int duration) {
		Time.sleep(duration);
		return this;
	}

	public ActionResult sleep(int minDuration, int maxDuration) {
		Time.sleep(minDuration, maxDuration);
		return this;
	}

	public ActionResult sleepUntil(BooleanSupplier condition, long timeout) {
		Time.sleepUntil(condition, timeout);
		return this;
	}
}
