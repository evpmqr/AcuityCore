package com.acuity.api.rs.wrappers.scene.elements;

import com.acuity.rs.api.RSSceneBoundaryDecor;
import com.acuity.rs.api.RSSceneTileDecor;
import com.google.common.base.Preconditions;
import com.sun.istack.internal.NotNull;

/**
 * Created by Zachary Herridge on 6/12/2017.
 */
public class SceneTileDecor {

    private RSSceneTileDecor rsSceneTileDecor;

    public SceneTileDecor(RSSceneTileDecor peer) {
        this.rsSceneTileDecor = Preconditions.checkNotNull(peer);
    }

    @NotNull
    public RSSceneTileDecor getRsSceneTileDecor() {
        return rsSceneTileDecor;
    }
}
