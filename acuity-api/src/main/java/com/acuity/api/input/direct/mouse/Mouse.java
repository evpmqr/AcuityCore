package com.acuity.api.input.direct.mouse;

import com.acuity.api.input.direct.mouse.impl.BasicMouseDriver;
import com.acuity.api.rs.utils.Random;
import com.acuity.api.rs.wrappers.common.locations.screen.ScreenLocation;

/**
 * Created by Zachary Herridge on 6/26/2017.
 */
public class Mouse {

    private static MouseDriver mouseDriver = new BasicMouseDriver();

    public static void click(ScreenLocation screenLocation){
        click(screenLocation, true);
    }

    public static void click(ScreenLocation screenLocation, boolean leftClick){
        mouseDriver.click(screenLocation, leftClick, Random.nextInt(10, 25));
    }
}
