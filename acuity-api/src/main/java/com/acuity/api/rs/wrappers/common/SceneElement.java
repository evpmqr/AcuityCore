package com.acuity.api.rs.wrappers.common;

import com.acuity.api.rs.interfaces.Locatable;
import com.acuity.api.rs.wrappers.peers.rendering.Model;
import com.acuity.rs.api.RSModel;
import com.acuity.rs.api.RSRenderable;

import java.util.Optional;

/**
 * Created by Zach on 6/24/2017.
 */
public interface SceneElement extends Locatable {

    static Optional<Model> getModel(RSRenderable rsRenderable, int sceneX, int sceneY, Integer orientation) {
        if (rsRenderable == null) return Optional.empty();

        Model lastModel;
        if (rsRenderable instanceof RSModel) lastModel = new Model((RSModel) rsRenderable);
        else lastModel = rsRenderable.getCachedModel();

        return Optional.of(lastModel)
                .map(model -> model.place(sceneX * 128, sceneY * 128))
                .map(model -> {
                    if (orientation != null) model.rotateTo(orientation);
                    return model;
                });
    }

    Optional<Model> getModel();
}
