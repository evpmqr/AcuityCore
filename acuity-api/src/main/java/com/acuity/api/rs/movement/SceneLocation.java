package com.acuity.api.rs.movement;

import com.acuity.api.rs.interfaces.Locatable;
import com.acuity.api.rs.utils.Scene;

/**
 * Created by Eclipseop.
 * Date: 6/8/2017.
 */
public class SceneLocation implements Locatable {

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

	@Override
	public int getSceneX() {
		return sceneX;
	}

	@Override
	public int getSceneY() {
		return sceneY;
	}

	@Override
	public int getPlane() {
		return plane;
	}
}
