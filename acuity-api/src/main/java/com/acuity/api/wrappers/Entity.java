package com.acuity.api.wrappers;

import com.acuity.api.interfaces.Interactive;
import com.acuity.rs.api.RSCacheableNode;
import com.acuity.rs.api.RSModel;
import com.acuity.rs.api.RSRenderable;

/**
 * Created by Eclipseop.
 * Date: 6/8/2017.
 */
public abstract class Entity<T extends RSRenderable> implements RSRenderable, Interactive {

	protected T object;

	public Entity(final T object) {
		this.object = object;
	}

	@Override
	public int getHeight() {
		return object.getHeight();
	}

	@Override
	public long getKey() {
		return object.getKey();
	}

	@Override
	public RSCacheableNode getNext() {
		return object.getNext();
	}

	@Override
	public RSCacheableNode getPrevious() {
		return object.getPrevious();
	}

	@Override
	public boolean invokeLinked() {
		return object.invokeLinked();
	}

	@Override
	public void invokeUnlink() {
		object.invokeUnlink();
	}

	@Override
	public RSModel invokeGetModel() {
		return object.invokeGetModel();
	}
}
