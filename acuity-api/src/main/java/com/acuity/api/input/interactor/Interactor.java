package com.acuity.api.input.interactor;

import com.acuity.api.input.interactor.impl.BasicInteractDriver;
import com.acuity.api.rs.interfaces.Interactive;
import com.acuity.api.rs.utils.ActionResult;

/**
 * Created by Zachary Herridge on 7/11/2017.
 */
public class Interactor {

    private static InteractionDriver interactionDriver = new BasicInteractDriver();

    public static ActionResult interact(Interactive interactive, String action){
        return interactionDriver.interact(interactive, action);
    }
}
