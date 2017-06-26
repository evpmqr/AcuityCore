package com.acuity.api.rs.wrappers.scene.elements;

import com.acuity.api.annotations.ClientInvoked;
import com.acuity.api.rs.movement.SceneLocation;
import com.acuity.api.rs.movement.WorldLocation;
import com.acuity.api.rs.wrappers.rendering.Model;
import com.acuity.rs.api.RSSceneBoundary;
import com.google.common.base.Preconditions;
import com.sun.istack.internal.NotNull;

import java.util.Optional;

/**
 * Created by Zachary Herridge on 6/12/2017.
 */
public class SceneBoundary implements ISceneElement {

    private RSSceneBoundary rsSceneBoundary;

    @ClientInvoked
    public SceneBoundary(RSSceneBoundary peer) {
        this.rsSceneBoundary = Preconditions.checkNotNull(peer);
    }

    @NotNull
    public RSSceneBoundary getRsSceneBoundary() {
        return rsSceneBoundary;
    }

    @Override
    public Optional<Model> getModel() {
        Model eitherModel = Optional.ofNullable(rsSceneBoundary.getEntity().getCachedModel()).orElseGet(() -> rsSceneBoundary.getRenderable2().getCachedModel());
        return Optional.ofNullable(eitherModel).map(model -> model.place(rsSceneBoundary.getSceneX() * 128, rsSceneBoundary.getSceneY() * 128));
    }

    @Override
    public WorldLocation getWorldLocation() {
        return new SceneLocation(rsSceneBoundary.getSceneX(), rsSceneBoundary.getSceneY()).getWorldLocation();
    }
}
