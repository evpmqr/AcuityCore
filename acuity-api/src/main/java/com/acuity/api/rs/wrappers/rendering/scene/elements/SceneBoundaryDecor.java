package com.acuity.api.rs.wrappers.rendering.scene.elements;

import com.acuity.api.annotations.ClientInvoked;
import com.acuity.api.rs.interfaces.Locatable;
import com.acuity.api.rs.movement.SceneLocation;
import com.acuity.api.rs.movement.WorldLocation;
import com.acuity.api.rs.utils.UIDs;
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

    @ClientInvoked
    public SceneBoundaryDecor(RSSceneBoundaryDecor peer) {
        this.rsSceneBoundaryDecor = Preconditions.checkNotNull(peer);
    }

    public Optional<Renderable> getEntity(){
        return Optional.ofNullable(rsSceneBoundaryDecor.getEntity()).map(RSRenderable::getWrapper);
    }

    public int getPlane(){
        return rsSceneBoundaryDecor.getPlane();
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

    public int getOrientation(){
        return rsSceneBoundaryDecor.getOrientation();
    }

    public int getSceneX(){
        return rsSceneBoundaryDecor.getSceneX();
    }

    public int getSceneY(){
        return rsSceneBoundaryDecor.getSceneY();
    }

    public UIDs.UID getUID(){
        return new UIDs.UID(rsSceneBoundaryDecor.getUID());
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
