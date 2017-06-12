package com.acuity.api.rs.wrappers.rendering.scene.elements;

import com.acuity.api.rs.interfaces.Locatable;
import com.acuity.api.rs.movement.SceneLocation;
import com.acuity.api.rs.movement.WorldLocation;
import com.acuity.api.rs.wrappers.rendering.Renderable;
import com.acuity.rs.api.RSRenderable;
import com.acuity.rs.api.RSSceneBoundaryDecor;
import com.google.common.base.Preconditions;
import com.sun.istack.internal.NotNull;

import java.util.Optional;

/**
 * Created by Zachary Herridge on 6/12/2017.
 */
public class SceneBoundaryDecor implements Locatable{

    private RSSceneBoundaryDecor rsSceneBoundaryDecor;

    public SceneBoundaryDecor(RSSceneBoundaryDecor peer) {
        this.rsSceneBoundaryDecor = Preconditions.checkNotNull(peer);
    }

    public Optional<Renderable> getEntity(){
        return Optional.ofNullable(rsSceneBoundaryDecor.getEntity()).map(RSRenderable::getWrapper);
    }

    public int getLevel(){// TODO: 6/12/2017 Rename to plane
        return rsSceneBoundaryDecor.getLevel();
    }

    public int getOffsetX(){
        return rsSceneBoundaryDecor.getOffsetX();
    }

    public int getOffsetY(){
        return rsSceneBoundaryDecor.getOffsetY();
    }

    public Optional<Renderable> getRenderable2(){
        return Optional.ofNullable(rsSceneBoundaryDecor.getRenderable2()).map(RSRenderable::getWrapper);
    }

    public int getRenderFlag(){
        return rsSceneBoundaryDecor.getRenderFlag();
    }

    public int getRenderInfoBitPacked(){
        return rsSceneBoundaryDecor.getRenderInfoBitPacked();
    }

    public int getRotation(){// TODO: 6/12/2017 Rename orientation
        return rsSceneBoundaryDecor.getRotation();
    }

    public int getSceneX(){
        return rsSceneBoundaryDecor.getSceneX();
    }

    public int getSceneY(){
        return rsSceneBoundaryDecor.getSceneY();
    }

    public int getUid(){// TODO: 6/12/2017 Rename UID
        return rsSceneBoundaryDecor.getUid();
    }

    @Override
    public WorldLocation getWorldLocation() {
        return new SceneLocation(getSceneX(), getSceneY()).getWorldLocation();
    }

    @NotNull
    public RSSceneBoundaryDecor getRsSceneBoundaryDecor() {
        return rsSceneBoundaryDecor;
    }
}
