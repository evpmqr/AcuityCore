package com.acuity.api.rs.movement;

import com.acuity.api.rs.interfaces.Locatable;

/**
 * Created by Eclipseop.
 * Date: 6/8/2017.
 */
public class SceneTile implements Locatable {

	private int x;
	private int y;
	private int plane;

	public SceneTile(int x, int y, int plane) {
		this.x = x;
		this.y = y;
		this.plane = plane;
	}

	public SceneTile(int x, int y) {
		this(x, y, 0);
	}

	public boolean isLoaded() {
		return getSceneX() > 3 && getSceneX() <= 98 && getSceneY() > 3 && getSceneY() <= 98;
	}

	@Override
	public int getSceneX() {
		return x;
	}

	@Override
	public int getSceneY() {
		return y;
	}

	@Override
	public int getPlane() {
		return plane;
	}
}
