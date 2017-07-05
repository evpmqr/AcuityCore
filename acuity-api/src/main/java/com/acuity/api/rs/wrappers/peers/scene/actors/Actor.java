package com.acuity.api.rs.wrappers.peers.scene.actors;

import com.acuity.api.annotations.ClientInvoked;
import com.acuity.api.rs.interfaces.Locatable;
import com.acuity.api.rs.interfaces.Nameable;
import com.acuity.api.rs.wrappers.common.locations.SceneLocation;
import com.acuity.api.rs.wrappers.common.locations.WorldLocation;
import com.acuity.api.rs.wrappers.peers.rendering.Model;
import com.acuity.api.rs.wrappers.peers.rendering.Renderable;
import com.acuity.api.rs.wrappers.peers.structures.NodeLinkedList;
import com.acuity.rs.api.RSActor;
import com.acuity.rs.api.RSNodeLinkedList;
import com.google.common.base.Preconditions;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Created by Eclipseop.
 * Date: 6/8/2017.
 */
public class Actor extends Renderable implements Locatable, Nameable {

    private static final Logger logger = LoggerFactory.getLogger(Actor.class);

    private RSActor rsActor;

    @ClientInvoked
    public Actor(@NotNull final RSActor peer) {
        super(peer);
        this.rsActor = Preconditions.checkNotNull(peer);
    }

    @Override
    public Optional<Model> getCachedModel() {
        return super.getCachedModel().map(model -> model.place(getStrictX(), getStrictY())).map(model -> model.rotateTo(getOrientation()));
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

    public Optional<NodeLinkedList> getHealthBars() {
        return Optional.ofNullable(rsActor.getHealthBars()).map(RSNodeLinkedList::getWrapper);
    }

    public int getIdlePoseAnimation() {
        return rsActor.getIdlePoseAnimation();
    }

    //null when no overhead text
    @Nullable
    public String getOverhead() {
        return rsActor.getOverhead();
    }

    public int getPoseAnimation() {
        return rsActor.getPoseAnimation();
    }

    public int getSpellAnimationID() {
        return rsActor.getSpellAnimationID();
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

    public boolean isAnimating() {
        return rsActor.getAnimation() != rsActor.getIdlePoseAnimation();
    }

    public SceneLocation getSceneLocation(){
        return new SceneLocation(getSceneX(), getSceneY(), getPlane());
    }

    @Override
    public WorldLocation getWorldLocation() {
        return getSceneLocation().getWorldLocation();
    }

    @NotNull
    public RSActor getRsActor() {
        return rsActor;
    }

    @Override
    public String getName() {
        return null;
    }
}
