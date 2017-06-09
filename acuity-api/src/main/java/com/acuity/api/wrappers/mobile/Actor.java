package com.acuity.api.wrappers.mobile;

import com.acuity.api.interfaces.Identifiable;
import com.acuity.api.interfaces.Locatable;
import com.acuity.api.wrappers.Renderable;
import com.acuity.client.Acuity;
import com.acuity.rs.api.RSActor;
import com.acuity.rs.api.RSCombatInfoList;

/**
 * Created by Eclipseop.
 * Date: 6/8/2017.
 */
public abstract class Actor extends Renderable implements Identifiable, Locatable {

    private RSActor rsActor;

    public Actor(final RSActor rsActor) {
        super(rsActor);
        this.rsActor = rsActor;
    }

    public int getSceneX() {
        return rsActor.getStrictX() >> 7;
    }

    public int getSceneY() {
        return rsActor.getStrictY() >> 7;
    }

    public int getStrictX() {
        return rsActor.getStrictX();
    }

    public int getStrictY() {
        return rsActor.getStrictY();
    }

    public int[] getPathX() {
        return rsActor.getPathX();
    }

    public int[] getPathY() {
        return rsActor.getPathY();
    }

    public int getPlane() {
        return Acuity.getClient().getPlane();
    }

    public int getOrientation() {
        return rsActor.getOrientation();
    }

    public int getTargetIndex() {
        return rsActor.getTargetIndex();
    }

    public int getAngle() {
        return rsActor.getAngle();
    }

    public int getAnimation() {
        return rsActor.getAnimation();
    }

    public int getQueueSize() {
        return rsActor.getQueueSize();
    }

    public int getActionAnimationDisable() {
        return rsActor.getActionAnimationDisable();
    }

    public int getActionFrame() {
        return rsActor.getActionFrame();
    }

    public RSCombatInfoList getHealthBars() {
        return rsActor.getHealthBars();
    }

    public int getIdlePoseAnimation() {
        return rsActor.getIdlePoseAnimation();
    }

    public String getOverhead() {
        return rsActor.getOverhead();
    }

    public int getPoseAnimation() {
        return rsActor.getPoseAnimation();
    }

    public int getSpellAnimationId() {
        return rsActor.getSpellAnimationId();
    }

    public int getSubAnimationFrame() {
        return rsActor.getSubAnimationFrame();
    }

    public boolean isInSequence() {
        return rsActor.isInSequence();
    }

    public int[] getHitsplatCycles() {
        return rsActor.getHitsplatCycles();
    }
}
