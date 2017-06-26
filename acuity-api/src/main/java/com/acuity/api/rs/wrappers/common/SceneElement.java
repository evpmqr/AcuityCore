package com.acuity.api.rs.wrappers.common;

import com.acuity.api.rs.interfaces.Locatable;
import com.acuity.api.rs.wrappers.peers.rendering.Model;

import java.util.Optional;

/**
 * Created by Zach on 6/24/2017.
 */
public interface SceneElement extends Locatable {

    Optional<Model> getModel();

}
