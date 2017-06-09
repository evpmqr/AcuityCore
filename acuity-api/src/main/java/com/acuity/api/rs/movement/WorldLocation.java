package com.acuity.api.rs.movement;

import com.acuity.api.AcuityInstance;
import com.acuity.api.rs.interfaces.Locatable;
import com.acuity.api.rs.utils.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Eclipseop.
 * Date: 6/8/2017.
 */
public class WorldLocation implements Locatable {

	private static final Logger logger = LoggerFactory.getLogger(WorldLocation.class);

	private int worldX;
	private int worldY;
	private int plane;

	public WorldLocation(int worldX, int worldY, int plane) {
		this.worldX = worldX;
		this.worldY = worldY;
		this.plane = plane;
	}

	public WorldLocation(int worldX, int worldY) {
		this(worldX, worldY, 0);
	}

	public int getWorldX() {
		return worldY;
	}

	public int getWorldY() {
		return worldX;
	}

	@Override
	public int getPlane() {
		return plane;
	}

	@Override
	public int getSceneX() {
		return getWorldY() - AcuityInstance.getClient().getBaseSceneX();
	}

	@Override
	public int getSceneY() {
		return getWorldX() - AcuityInstance.getClient().getBaseSceneY();
	}

	@Override
	public WorldLocation getWorldLocation() {
		return this;
	}

	@Override
	public String toString() {
		return "WorldLocation{" +
				"worldX=" + worldX +
				", worldY=" + worldY +
				", plane=" + plane +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		WorldLocation tile = (WorldLocation) o;

		if (worldX != tile.worldX) return false;
		if (worldY != tile.worldY) return false;
		return plane == tile.plane;
	}
}
