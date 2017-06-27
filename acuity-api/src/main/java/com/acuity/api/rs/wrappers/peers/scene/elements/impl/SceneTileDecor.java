package com.acuity.api.rs.wrappers.peers.scene.elements.impl;

import com.acuity.api.annotations.ClientInvoked;
import com.acuity.api.rs.utils.UIDs;
import com.acuity.api.rs.wrappers.common.SceneLocation;
import com.acuity.api.rs.wrappers.common.WorldLocation;
import com.acuity.api.rs.wrappers.peers.rendering.Model;
import com.acuity.api.rs.wrappers.common.SceneElement;
import com.acuity.rs.api.RSSceneTileDecor;
import com.google.common.base.Preconditions;
import com.sun.istack.internal.NotNull;

import java.util.Optional;

/**
 * Created by Zachary Herridge on 6/12/2017.
 */
public class SceneTileDecor implements SceneElement {

    private RSSceneTileDecor rsSceneTileDecor;

    @ClientInvoked
    public SceneTileDecor(RSSceneTileDecor peer) {
        this.rsSceneTileDecor = Preconditions.checkNotNull(peer);
    }

    public SceneLocation getSceneLocation(){
        return new SceneLocation(rsSceneTileDecor.getSceneX(), rsSceneTileDecor.getSceneY(), rsSceneTileDecor.getLevel());
    }

    @Override
    public WorldLocation getWorldLocation() {
        return getSceneLocation().getWorldLocation();
    }

    public int getID(){
        return getUID().getEntityID();
    }

    public UIDs.UID getUID() {
        return new UIDs.UID(rsSceneTileDecor.getUid());
    }

    @Override
    public Optional<Model> getModel() {
        return com.acuity.api.rs.wrappers.common.SceneElement.getModel(
                rsSceneTileDecor.getEntity(),
                rsSceneTileDecor.getSceneX(),
                rsSceneTileDecor.getSceneY(),
                null);
    }

    @NotNull
    public RSSceneTileDecor getRsSceneTileDecor() {
        return rsSceneTileDecor;
    }
}
