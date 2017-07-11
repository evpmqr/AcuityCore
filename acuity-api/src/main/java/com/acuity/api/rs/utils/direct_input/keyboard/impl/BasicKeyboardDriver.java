package com.acuity.api.rs.utils.direct_input.keyboard.impl;

import com.acuity.api.AcuityInstance;
import com.acuity.api.rs.utils.direct_input.keyboard.KeyboardDriver;

/**
 * Created by Zachary Herridge on 7/11/2017.
 */
public class BasicKeyboardDriver implements KeyboardDriver {

    @Override
    public void type(char key) {
        AcuityInstance.getAppletManager().getKeyboardMiddleMan().dispatchTypeKey(key, 0);
    }
}
