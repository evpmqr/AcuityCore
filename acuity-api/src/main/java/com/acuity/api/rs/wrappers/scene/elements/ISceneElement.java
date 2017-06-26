package com.acuity.api.rs.wrappers.scene.elements;

import com.acuity.api.rs.interfaces.Locatable;
import com.acuity.api.rs.wrappers.rendering.Model;

import java.util.Optional;

/**
 * Created by Zach on 6/24/2017.
 */
public interface ISceneElement extends Locatable {

    Optional<Model> getModel();

}
