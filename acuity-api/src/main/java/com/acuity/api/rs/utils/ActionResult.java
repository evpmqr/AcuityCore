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
		Delay.delay(duration);
		return this;
	}

	public ActionResult sleep(int minDuration, int maxDuration) {
		Delay.delay(minDuration, maxDuration);
		return this;
	}

	public ActionResult sleepUntil(BooleanSupplier condition, long timeout) {
		Delay.delayUntil(condition, timeout);
		return this;
	}
}
