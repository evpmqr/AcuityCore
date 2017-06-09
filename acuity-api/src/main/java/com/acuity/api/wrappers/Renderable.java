package com.acuity.api.wrappers;

import com.acuity.api.interfaces.Interactive;
import com.acuity.rs.api.RSModel;
import com.acuity.rs.api.RSRenderable;

/**
 * Created by Eclipseop.
 * Date: 6/8/2017.
 */
public abstract class Renderable implements Interactive {

	protected RSRenderable rsRenderable;

	public Renderable(final RSRenderable rsRenderable) {
		this.rsRenderable = rsRenderable;
	}

	public int getHeight() {
		return rsRenderable.getHeight();
	}

	public RSModel invokeGetModel() {
		return rsRenderable.invokeGetModel();
	}
}
