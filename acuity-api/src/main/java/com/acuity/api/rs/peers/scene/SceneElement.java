package com.acuity.api.rs.peers.scene;

import com.acuity.api.rs.peers.Wrapper;
import com.acuity.rs.api.RSSceneElement;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Zachary Herridge on 6/9/2017.
 */
public class SceneElement<T extends RSSceneElement> extends Wrapper<T> {

    private static final Logger logger = LoggerFactory.getLogger(SceneElement.class);

    public SceneElement(T peer) {
        super(peer);
    }

}
