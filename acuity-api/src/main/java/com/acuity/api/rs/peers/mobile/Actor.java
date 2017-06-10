package com.acuity.api.rs.peers.mobile;

import com.acuity.api.rs.interfaces.Locatable;
import com.acuity.api.rs.movement.SceneLocation;
import com.acuity.api.rs.movement.WorldLocation;
import com.acuity.api.rs.peers.Renderable;
import com.acuity.rs.api.RSActor;
import com.acuity.rs.api.RSCombatInfoList;
import com.google.common.base.Preconditions;
import com.sun.istack.internal.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Eclipseop.
 * Date: 6/8/2017.
 */
public abstract class Actor<T extends RSActor> extends Renderable<T> implements Locatable {

    private static final Logger logger = LoggerFactory.getLogger(Actor.class);
    
    public Actor(@NotNull final T peer) {
        super(peer);
    }

    public int getSceneX() {
        return peer.getStrictX() >> 7;
    }

    public int getSceneY() {
        return peer.getStrictY() >> 7;
    }

    public int getStrictX() {
        return peer.getStrictX();
    }

    public int getStrictY() {
        return peer.getStrictY();
    }

    public int[] getPathX() {
        return peer.getPathX();
    }

    public int[] getPathY() {
        return peer.getPathY();
    }

    public int getOrientation() {
        return peer.getOrientation();
    }

    public int getTargetIndex() {
        return peer.getTargetIndex();
    }

    public int getAngle() {
        return peer.getAngle();
    }

    public int getAnimation() {
        return peer.getAnimation();
    }

    public int getQueueSize() {
        return peer.getQueueSize();
    }

    public int getActionAnimationDisable() {
        return peer.getActionAnimationDisable();
    }

    public int getActionFrame() {
        return peer.getActionFrame();
    }

    public RSCombatInfoList getHealthBars() {
        return peer.getHealthBars(); // TODO: 6/9/2017 Add wrapper
    }

    public int getIdlePoseAnimation() {
        return peer.getIdlePoseAnimation();
    }

    public String getOverhead() {
        return peer.getOverhead(); // TODO: 6/9/2017 Figure out if default state is 'null'. If it is add documentation and @Nullable
    }

    public int getPoseAnimation() {
        return peer.getPoseAnimation();
    }

    public int getSpellAnimationId() {
        return peer.getSpellAnimationId();
    }

    public int getSubAnimationFrame() {
        return peer.getSubAnimationFrame();
    }

    public boolean isInSequence() {
        return peer.isInSequence();
    }

    public int[] getHitsplatCycles() {
        return peer.getHitsplatCycles();
    }

    public boolean isAnimating() {
        return peer.getAnimation() != peer.getIdlePoseAnimation();
    }

    public SceneLocation getSceneLocation(){
        return new SceneLocation(getSceneX(), getSceneY(), getPlane());
    }

    @Override
    public WorldLocation getWorldLocation() {
        return getSceneLocation().getWorldLocation();
    }
}
