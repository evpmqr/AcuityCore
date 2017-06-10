package com.acuity.api.rs.peers;

import com.acuity.api.rs.interfaces.Interactive;
import com.acuity.rs.api.RSModel;
import com.acuity.rs.api.RSRenderable;
import com.google.common.base.Preconditions;
import com.sun.istack.internal.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Eclipseop.
 * Date: 6/8/2017.
 */
public abstract class Renderable implements Interactive {

    private static final Logger logger = LoggerFactory.getLogger(Renderable.class);

	protected RSRenderable rsRenderable;

	public Renderable(@NotNull final RSRenderable peer) {
		this.rsRenderable = Preconditions.checkNotNull(peer);
	}

	public int getHeight() {
		return rsRenderable.getHeight();
	}

	public RSModel invokeGetModel() {
	    logger.trace("Invoking getModel() on RSModel.");
		return rsRenderable.invokeGetModel();// TODO: 6/9/2017 Add wrapper
    }

    public RSRenderable getRsRenderable() {
        return rsRenderable;
    }
}
