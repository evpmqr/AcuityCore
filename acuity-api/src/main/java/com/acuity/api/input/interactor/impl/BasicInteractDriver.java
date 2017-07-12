package com.acuity.api.input.interactor.impl;

import com.acuity.api.input.direct.mouse.Mouse;
import com.acuity.api.input.interactor.InteractionDriver;
import com.acuity.api.rs.interfaces.Interactive;
import com.acuity.api.rs.utils.ActionResult;
import com.acuity.api.rs.wrappers.common.locations.screen.ScreenLocation;
import com.acuity.api.rs.wrappers.common.locations.screen.ScreenLocationShape;

import java.util.Optional;

/**
 * Created by Zachary Herridge on 7/11/2017.
 */
public class BasicInteractDriver implements InteractionDriver {

    @Override
    public ActionResult interact(Interactive interactive, String action) {
        Optional<ScreenLocationShape> screenLocationShape = interactive.getScreenTargetSupplier().get();
        if (screenLocationShape.isPresent()){
            ScreenLocation[] points = screenLocationShape.get().getPoints();
            if (points == null || points.length == 0) return ActionResult.FAILURE;
            Mouse.click(points[0]);
            return ActionResult.SUCCESS;
        }
        return ActionResult.FAILURE;
    }
}
