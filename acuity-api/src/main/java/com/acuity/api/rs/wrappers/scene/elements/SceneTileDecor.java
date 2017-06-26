package com.acuity.api.rs.wrappers.scene.elements;

import com.acuity.api.annotations.ClientInvoked;
import com.acuity.api.rs.movement.SceneLocation;
import com.acuity.api.rs.movement.WorldLocation;
import com.acuity.api.rs.wrappers.rendering.Model;
import com.acuity.rs.api.RSSceneBoundaryDecor;
import com.acuity.rs.api.RSSceneTileDecor;
import com.google.common.base.Preconditions;
import com.sun.istack.internal.NotNull;

import java.util.Optional;

/**
 * Created by Zachary Herridge on 6/12/2017.
 */
public class SceneTileDecor implements ISceneElement{

    private RSSceneTileDecor rsSceneTileDecor;

    @ClientInvoked
    public SceneTileDecor(RSSceneTileDecor peer) {
        this.rsSceneTileDecor = Preconditions.checkNotNull(peer);
    }

    @NotNull
    public RSSceneTileDecor getRsSceneTileDecor() {
        return rsSceneTileDecor;
    }

    @Override
    public Optional<Model> getModel() {
        return Optional.ofNullable(rsSceneTileDecor.getEntity().getCachedModel());
    }

    @Override
    public WorldLocation getWorldLocation() {
        return new SceneLocation(rsSceneTileDecor.getSceneX(), rsSceneTileDecor.getSceneY()).getWorldLocation();    }
}
