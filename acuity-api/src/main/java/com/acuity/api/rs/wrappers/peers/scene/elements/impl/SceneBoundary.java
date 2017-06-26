package com.acuity.api.rs.wrappers.peers.scene.elements.impl;

import com.acuity.api.annotations.ClientInvoked;
import com.acuity.api.rs.wrappers.common.*;
import com.acuity.api.rs.wrappers.peers.rendering.Model;
import com.acuity.rs.api.RSSceneBoundary;
import com.google.common.base.Preconditions;
import com.sun.istack.internal.NotNull;

import java.util.Optional;

/**
 * Created by Zachary Herridge on 6/12/2017.
 */
public class SceneBoundary implements com.acuity.api.rs.wrappers.common.SceneElement {

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
        return Optional.ofNullable(eitherModel).map(model -> model
                .place(rsSceneBoundary.getSceneX() * 128, rsSceneBoundary.getSceneY() * 128));
    }

    @Override
    public WorldLocation getWorldLocation() {
        return new SceneLocation(rsSceneBoundary.getSceneX(), rsSceneBoundary.getSceneY()).getWorldLocation();
    }
}
