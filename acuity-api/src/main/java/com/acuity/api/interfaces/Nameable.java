package com.acuity.api.interfaces;

/**
 * Created by Eclipseop.
 * Date: 6/8/2017.
 */
public interface Nameable {

	default String getName() {
		throw new UnsupportedOperationException();
	}

	default String getNullSafeName() {
		final String name = getName();
		return name == null ? "null" : name;
	}
}
