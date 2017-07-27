package com.acuity.api.applet.input.impl;

import com.acuity.api.applet.input.InputMiddleMan;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Created by Zachary Herridge on 7/27/2017.
 */
public class FocusMiddleMan implements InputMiddleMan, FocusListener {
    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {

    }

    @Override
    public boolean insertInto(Component component) {
        return false;
    }
}
