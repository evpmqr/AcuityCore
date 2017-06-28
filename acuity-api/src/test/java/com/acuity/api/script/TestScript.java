package com.acuity.api.script;

import com.acuity.api.script.impl.AcuityScript;

/**
 * Created by Zachary Herridge on 6/28/2017.
 */
public class TestScript extends AcuityScript {
    @Override
    public void loop() {
        System.out.println("Loop: " + System.currentTimeMillis());
    }
}
