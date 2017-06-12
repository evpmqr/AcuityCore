package com.acuity.api.rs.wrappers.scene.elements;

import com.acuity.rs.api.RSSceneBoundary;
import com.google.common.base.Preconditions;
import com.sun.istack.internal.NotNull;

/**
 * Created by Zachary Herridge on 6/12/2017.
 */
public class SceneBoundary {

    private RSSceneBoundary rsSceneBoundary;

    public SceneBoundary(RSSceneBoundary peer) {
        this.rsSceneBoundary = Preconditions.checkNotNull(peer);
    }

    @NotNull
    public RSSceneBoundary getRsSceneBoundary() {
        return rsSceneBoundary;
    }
}
