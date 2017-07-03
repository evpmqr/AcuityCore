package com.acuity.api.rs.wrappers.peers.composite;

import com.acuity.api.AcuityInstance;
import com.acuity.api.annotations.ClientInvoked;
import com.acuity.api.rs.wrappers.peers.structures.CacheableNode;
import com.acuity.rs.api.RSSceneElementComposite;
import com.sun.istack.internal.NotNull;

/**
 * Created by Zachary Herridge on 6/27/2017.
 */
public class SceneElementComposite extends CacheableNode{

    private RSSceneElementComposite rsSceneElementComposite;

    @ClientInvoked
    public SceneElementComposite(RSSceneElementComposite rsSceneElementComposite) {
        super(rsSceneElementComposite);
        this.rsSceneElementComposite = rsSceneElementComposite;
    }

    @NotNull
    public RSSceneElementComposite getRsSceneElementComposite() {
        return rsSceneElementComposite;
    }
}
