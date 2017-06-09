package com.acuity.api.rs.utils;

import com.acuity.api.AcuityInstance;

/**
 * Created by Zachary Herridge on 6/9/2017.
 */
public class Map {

    public static int getScale(){
        return AcuityInstance.getClient().getMapScale();
    }

    public static int getRotation(){
        return AcuityInstance.getClient().getMapRotation();
    }

    public static int getOffset() {
        return AcuityInstance.getClient().getMinimapOffset();
    }
}
