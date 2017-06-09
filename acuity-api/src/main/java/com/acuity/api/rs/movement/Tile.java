package com.acuity.api.rs.movement;

import com.acuity.api.RSInstance;
import com.acuity.api.rs.interfaces.Locatable;

/**
 * Created by Eclipseop.
 * Date: 6/8/2017.
 */
public class Tile implements Locatable {

	private int x;
	private int y;
	private int plane;

	public Tile(int x, int y, int plane) {
		this.x = x;
		this.y = y;
		this.plane = plane;
	}

	public Tile(int x, int y) {
		this(x, y, 0);
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}

	@Override
	public int getPlane() {
		return plane;
	}

	@Override
	public int getSceneX() {
		return getX() - RSInstance.getClient().getBaseSceneX();
	}

	@Override
	public int getSceneY() {
		return getY() - RSInstance.getClient().getBaseSceneY();
	}

	@Override
	public Tile toLocation() {
		return this;
	}

	@Override
	public String toString() {
		return "Tile{" +
				"x=" + x +
				", y=" + y +
				", plane=" + plane +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Tile tile = (Tile) o;

		if (x != tile.x) return false;
		if (y != tile.y) return false;
		return plane == tile.plane;
	}
}
