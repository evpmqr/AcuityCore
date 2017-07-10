package com.acuity.api.rs.wrappers.peers.scene.actors;

import com.acuity.api.AcuityInstance;
import com.acuity.api.annotations.ClientInvoked;
import com.acuity.api.rs.interfaces.Clickable;
import com.acuity.api.rs.interfaces.Locatable;
import com.acuity.api.rs.interfaces.Nameable;
import com.acuity.api.rs.utils.Scene;
import com.acuity.api.rs.wrappers.common.locations.SceneLocation;
import com.acuity.api.rs.wrappers.common.locations.StrictLocation;
import com.acuity.api.rs.wrappers.common.locations.WorldLocation;
import com.acuity.api.rs.wrappers.common.locations.screen.ScreenLocationShape;
import com.acuity.api.rs.wrappers.peers.rendering.Model;
import com.acuity.api.rs.wrappers.peers.rendering.Renderable;
import com.acuity.api.rs.wrappers.peers.rendering.bounding_boxes.AxisAlignedBoundingBox;
import com.acuity.api.rs.wrappers.peers.structures.NodeLinkedList;
import com.acuity.rs.api.RSActor;
import com.acuity.rs.api.RSNodeLinkedList;
import com.google.common.base.Preconditions;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Created by Eclipseop.
 * Date: 6/8/2017.
 */
public abstract class Actor extends Renderable implements Locatable, Nameable {

    private static final Logger logger = LoggerFactory.getLogger(Actor.class);

    private RSActor rsActor;

    @ClientInvoked
    public Actor(@NotNull final RSActor peer) {
        super(peer);
        this.rsActor = Preconditions.checkNotNull(peer);
    }

    @Override
    public Optional<Model> getCachedModel() {
        StrictLocation strictLocation = getStrictLocation();
        return super.getCachedModel()
                .map(model -> model.place(strictLocation.getX(), strictLocation.getY()))
                .map(model -> model.rotateTo(getOrientation()));
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

    public boolean isInAnimationSequence() {
        return rsActor.isInSequence();
    }

    public int[] getHitsplatCycles() {
        return rsActor.getHitsplatCycles();
    }

    public boolean isAnimating() {
        return rsActor.getAnimation() != rsActor.getIdlePoseAnimation();
    }

    public StrictLocation getStrictLocation(){
        return new StrictLocation(rsActor.getStrictX(), rsActor.getStrictY(), Scene.getPlane());
    }

    public SceneLocation getSceneLocation(){
        return getStrictLocation().getSceneLocation();
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
    public Supplier<Optional<ScreenLocationShape>> getScreenTargetSupplier() {
        if (!AcuityInstance.getSettings().isModelInteractionsEnabled()){
            return getBoundingBox().map(AxisAlignedBoundingBox::getScreenTargetSupplier).orElse(Clickable.EMPTY_SUPPLIER);
        }
        return Clickable.EMPTY_SUPPLIER;
    }
}
