package com.acuity.api.rs.peers;

import com.acuity.api.rs.interfaces.Interactive;
import com.acuity.rs.api.RSModel;
import com.acuity.rs.api.RSRenderable;
import com.google.common.base.Preconditions;
import com.sun.istack.internal.NotNull;

/**
 * Created by Eclipseop.
 * Date: 6/8/2017.
 */
public abstract class Renderable implements Interactive {

	protected RSRenderable rsRenderable;

	public Renderable(@NotNull final RSRenderable peer) {
        Preconditions.checkNotNull(peer);
		this.rsRenderable = peer;
	}

	public int getHeight() {
		return rsRenderable.getHeight();
	}

	public RSModel invokeGetModel() {
		return rsRenderable.invokeGetModel();// TODO: 6/9/2017 Add wrapper
    }

    public RSRenderable getRsRenderable() {
        return rsRenderable;
    }
}
