package com.acuity.api.rs.wrappers.peers.composite;

import com.acuity.api.rs.wrappers.peers.structures.CacheableNode;
import com.acuity.rs.api.RSSceneElementComposite;

/**
 * Created by Zachary Herridge on 6/27/2017.
 */
public class SceneElementComposite extends CacheableNode{

    private RSSceneElementComposite rsSceneElementComposite;

    public SceneElementComposite(RSSceneElementComposite rsSceneElementComposite) {
        super(rsSceneElementComposite);
        this.rsSceneElementComposite = rsSceneElementComposite;
    }

    public RSSceneElementComposite getRsSceneElementComposite() {
        return rsSceneElementComposite;
    }
}
