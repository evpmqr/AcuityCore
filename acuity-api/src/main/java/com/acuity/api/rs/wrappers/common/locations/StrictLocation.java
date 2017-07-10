package com.acuity.api.rs.wrappers.common.locations;

import com.acuity.api.rs.interfaces.Locatable;
import com.acuity.api.rs.utils.Projection;
import com.acuity.api.rs.utils.Scene;
import com.acuity.api.rs.wrappers.common.locations.screen.ScreenLocation;

import java.util.Optional;

/**
 * Created by Zach on 7/1/2017.
 */
public class StrictLocation implements Locatable{

    private int x, y, plane;
    private int baseX, baseY;

    public StrictLocation(int x, int y, int plane) {
        this(x, y, plane, Scene.getBaseX(), Scene.getBaseY());
    }

    public StrictLocation(int x, int y, int plane, int baseX, int baseY) {
        this.x = x;
        this.y = y;
        this.plane = plane;
        this.baseX = baseX;
        this.baseY = baseY;
    }

    public int getPlane() {
        return plane;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public SceneLocation getSceneLocation(){
        return new SceneLocation(x / Projection.TILE_PIXEL_SIZE, y / Projection.TILE_PIXEL_SIZE, plane, baseX, baseY);
    }

    @Override
    public WorldLocation getWorldLocation(){
        return getSceneLocation().getWorldLocation();
    }

    @Override
    public Optional<ScreenLocation> getScreenLocation() {
        return Projection.strictToScreen(this);
    }


}
