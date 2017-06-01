package com.acuity.api;

import com.acuity.rs.api.RSWorld;
import com.sun.istack.internal.NotNull;

/**
 * Created by Zachary Herridge on 6/1/2017.
 */
public class World {

    private RSWorld rsWorld;

    public World(@NotNull RSWorld rsWorld) {
        this.rsWorld = rsWorld;
    }

    public int getWorld(){
        return rsWorld.getWorld();
    }
}
