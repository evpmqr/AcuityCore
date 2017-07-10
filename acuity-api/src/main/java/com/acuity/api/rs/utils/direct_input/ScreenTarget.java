package com.acuity.api.rs.utils.direct_input;

import com.acuity.api.rs.wrappers.common.locations.ScreenLocation;

/**
 * Created by Zachary Herridge on 7/10/2017.
 */
public class ScreenTarget {

    private ScreenLocation[] points;

    public ScreenTarget(ScreenLocation... points) {
        this.points = points;
    }

    public ScreenLocation[] getPoints() {
        return points;
    }
}
