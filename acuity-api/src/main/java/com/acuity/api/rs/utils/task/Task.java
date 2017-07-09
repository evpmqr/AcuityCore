package com.acuity.api.rs.utils.task;

/**
 * Created by Eclipseop.
 * Date: 7/8/2017.
 */
public abstract class Task {

	public abstract boolean validate();

	public abstract int loop();
}
