package com.acuity.api.input.interactor.impl;

import com.acuity.api.input.interactor.InteractionDriver;
import com.acuity.api.rs.interfaces.Interactive;
import com.acuity.api.rs.utils.ActionResult;

/**
 * Created by Zachary Herridge on 7/11/2017.
 */
public class BasicInteractDriver implements InteractionDriver {
    @Override
    public ActionResult interact(Interactive interactive, String action) {
        return ActionResult.FAILURE;
    }
}
