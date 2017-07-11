package com.acuity.api.input.direct.keyboard;

import com.acuity.api.input.direct.keyboard.impl.BasicKeyboardDriver;

/**
 * Created by Zachary Herridge on 6/26/2017.
 */
public class Keyboard {

    private static KeyboardDriver keyboardDriver = new BasicKeyboardDriver();

    public static void type(char key){
        keyboardDriver.type(key);
    }
}
