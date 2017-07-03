package com.acuity.api.rs.wrappers.common;

import com.acuity.api.AcuityInstance;
import com.acuity.api.rs.interfaces.Locatable;
import com.acuity.api.rs.utils.Varps;
import com.acuity.api.rs.wrappers.peers.composite.SceneElementComposite;
import com.acuity.api.rs.wrappers.peers.rendering.Model;
import com.acuity.rs.api.RSModel;
import com.acuity.rs.api.RSRenderable;
import com.acuity.rs.api.RSSceneElementComposite;

import java.util.Optional;

/**
 * Created by Zach on 6/24/2017.
 */
public interface SceneElement extends Locatable {

    static Optional<Model> getModel(RSRenderable rsRenderable, StrictLocation location, Integer orientation) {
        if (rsRenderable == null) return Optional.empty();

        Model lastModel;
        if (rsRenderable instanceof RSModel) lastModel = new Model((RSModel) rsRenderable);
        else lastModel = rsRenderable.getCachedModel();

        return Optional.ofNullable(lastModel)
                .map(model -> model.place(location.getX() , location.getY()))
                .map(model -> {
                    if (orientation != null) model.rotateTo(orientation);
                    return model;
                });
    }

    Optional<Model> getModel();


    static Optional<SceneElementComposite> getComposite(int id, int[] transformIDs, int varpbitIndex, int varpIndex){
        RSSceneElementComposite rsSceneElementComposite = AcuityInstance.getClient().getRsClient().invokeGetObjectDefinition(id);


        if (transformIDs != null){
            int index = -1;
            if (varpbitIndex != -1){
                Varps.getBit(varpbitIndex);
            }

        }


        return Optional.ofNullable(rsSceneElementComposite).map(RSSceneElementComposite::getWrapper);
    }

}
