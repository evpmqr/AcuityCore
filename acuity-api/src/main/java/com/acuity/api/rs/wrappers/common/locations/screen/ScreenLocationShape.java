package com.acuity.api.rs.wrappers.common.locations.screen;

/**
 * Created by Zachary Herridge on 7/10/2017.
 */
public class ScreenLocationShape {

    private ScreenLocation[] points;

    public ScreenLocationShape(ScreenLocation... points) {
        this.points = points;
    }

    public ScreenLocation[] getPoints() {
        return points;
    }
}
