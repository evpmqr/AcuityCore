package com.acuity.api.rs.utils.direct_input.keyboard;

import com.acuity.api.rs.utils.direct_input.keyboard.impl.BasicKeyboardDriver;

/**
 * Created by Zachary Herridge on 6/26/2017.
 */
public class Keyboard {

    private static KeyboardDriver keyboardDriver = new BasicKeyboardDriver();

    public static void type(char key){
        keyboardDriver.type(key);
    }
}
