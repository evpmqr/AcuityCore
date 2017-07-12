package com.acuity.api.input.interactor;

import com.acuity.api.rs.interfaces.Interactive;
import com.acuity.api.rs.utils.ActionResult;

/**
 * Created by Zachary Herridge on 7/11/2017.
 */
public interface InteractionDriver {

    ActionResult interact(Interactive interactive, String action);

}
