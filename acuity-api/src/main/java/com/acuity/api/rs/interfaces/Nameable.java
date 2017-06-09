package com.acuity.api.rs.interfaces;

/**
 * Created by Eclipseop.
 * Date: 6/8/2017.
 */
public interface Nameable {

	String getName();

	default String getNullSafeName() {
		return String.valueOf(getName());
	}
}
