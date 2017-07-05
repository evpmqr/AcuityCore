package com.acuity.api.rs.wrappers.common.locations;

import com.acuity.api.rs.interfaces.Locatable;
import com.acuity.api.rs.utils.Projection;
import com.acuity.api.rs.utils.Scene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

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

	public SceneLocation toCurrentSceneLocation(){
		return new SceneLocation(getWorldX() - Scene.getBaseX(), getWorldY() - Scene.getBaseY(), getPlane());
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
	public WorldLocation getWorldLocation() {
		return this;
	}
}
