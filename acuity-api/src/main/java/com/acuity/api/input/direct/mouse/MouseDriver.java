package com.acuity.api.input.direct.mouse;

import com.acuity.api.rs.wrappers.common.locations.screen.ScreenLocation;

/**
 * Created by Zachary Herridge on 7/11/2017.
 */
public interface MouseDriver {

    void click(ScreenLocation screenLocation, boolean leftClick, int delayMills);

}
