package com.acuity.api.rs.wrappers.common.locations.screen;

/**
 * Created by Zachary Herridge on 7/10/2017.
 */
public class ScreenLocationShape<T extends ScreenLocation> {

    private T[] points;

    public ScreenLocationShape(T... points) {
        this.points = points;
    }

    public T[] getPoints() {
        return points;
    }
}
