package com.acuity.api.rs.movement;

import com.acuity.api.rs.interfaces.Locatable;
import com.acuity.api.rs.utils.Scene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Eclipseop.
 * Date: 6/8/2017.
 */
public class SceneLocation implements Locatable {

	private static final Logger logger = LoggerFactory.getLogger(SceneLocation.class);

	private int baseX, baseY;
	private int sceneX;
	private int sceneY;
	private int plane;

	public SceneLocation(int x, int y, int plane) {
		this.sceneX = x;
		this.sceneY = y;
		this.plane = plane;
		this.baseX = Scene.getBaseX();
		this.baseY = Scene.getBaseY();
	}

	public SceneLocation(int x, int y) {
		this(x, y, 0);
	}

	public boolean isLoaded() {
		return getSceneX() > 3 && getSceneX() <= 98 && getSceneY() > 3 && getSceneY() <= 98;
	}

	public int getSceneX() {
		return sceneX;
	}

	public int getSceneY() {
		return sceneY;
	}

	public int getBaseX() {
		return baseX;
	}

	public int getBaseY() {
		return baseY;
	}

	@Override
	public int getPlane() {
		return plane;
	}

	@Override
	public WorldLocation getWorldLocation() {
		return new WorldLocation(getSceneX() + getBaseX(), getSceneY() + getBaseY(), getPlane());
	}
}
