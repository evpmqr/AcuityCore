package com.acuity.api.wrappers.mobile;

import com.acuity.api.interfaces.Identifiable;
import com.acuity.api.interfaces.Locatable;
import com.acuity.api.wrappers.Entity;
import com.acuity.client.Acuity;
import com.acuity.rs.api.RSActor;
import com.acuity.rs.api.RSCombatInfoList;

/**
 * Created by Eclipseop.
 * Date: 6/8/2017.
 */
public abstract class Mobile<T extends RSActor> extends Entity<T> implements RSActor, Identifiable, Locatable {

	public Mobile(final T object) {
		super(object);
	}

	@Override
	public int getSceneX() {
		return object.getStrictX() >> 7;
	}

	@Override
	public int getSceneY() {
		return object.getStrictY() >> 7;
	}

	@Override
	public int getStrictX() {
		return object.getStrictX();
	}

	@Override
	public int getStrictY() {
		return object.getStrictY();
	}

	@Override
	public int[] getPathX() {
		return object.getPathX();
	}

	@Override
	public int[] getPathY() {
		return object.getPathY();
	}

	@Override
	public int getPlane() {
		return Acuity.getClient().getPlane();
	}

	@Override
	public int getOrientation() {
		return object.getOrientation();
	}

	@Override
	public int getTargetIndex() {
		return object.getTargetIndex();
	}

	@Override
	public int getAngle() {
		return object.getAngle();
	}

	@Override
	public int getAnimation() {
		return object.getAnimation();
	}

	@Override
	public int getQueueSize() {
		return object.getQueueSize();
	}

	@Override
	public int getActionAnimationDisable() {
		return object.getActionAnimationDisable();
	}

	@Override
	public int getActionFrame() {
		return object.getActionFrame();
	}

	@Override
	public RSCombatInfoList getHealthBars() {
		return object.getHealthBars();
	}

	@Override
	public int getIdlePoseAnimation() {
		return object.getIdlePoseAnimation();
	}

	@Override
	public String getOverhead() {
		return object.getOverhead();
	}

	@Override
	public int getPoseAnimation() {
		return object.getPoseAnimation();
	}

	@Override
	public int getSpellAnimationId() {
		return object.getSpellAnimationId();
	}

	@Override
	public int getSubAnimationFrame() {
		return object.getSubAnimationFrame();
	}

	@Override
	public boolean isInSequence() {
		return object.isInSequence();
	}

	@Override
	public int[] getHitsplatCycles() {
		return object.getHitsplatCycles();
	}
}
