package com.acuity.rs.api;

import com.acuity.rs.mapping.Inject;

/**
 * Created by Zachary Herridge on 6/2/2017.
 */
@Inject("RSCoordinates")
public interface RSCoordinates {

    @Inject("worldX")
    int getWorldX();

    @Inject("worldY")
    int getWorldY();

    @Inject("plane")
    int getPlane();
}
