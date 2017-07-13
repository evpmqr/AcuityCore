package com.acuity.api.applet.input;

import com.acuity.api.Events;
import com.acuity.api.meta.tile_dumper.TileDumper;
import com.acuity.api.rs.utils.Random;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Zach on 6/17/2017.
 */
public class KeyboardMiddleMan implements KeyListener {

    private static Logger logger = LoggerFactory.getLogger(KeyboardMiddleMan.class);

    private Component component;
    private KeyListener output;

    public void replace(Component component) {
        this.component = component;
        if (component.getKeyListeners().length > 0) {
            output = component.getKeyListeners()[0];
        }
        component.addKeyListener(this);
        logger.info("Replaced keyboard of {}.", component);
    }

    public synchronized void pressEnter() {
        dispatchPressKey(KeyEvent.VK_ENTER, 20);
    }

    public synchronized void sendText(String text, boolean pressEnter, int minDelay, int maxDelay) {
        Preconditions.checkNotNull(text);
        Preconditions.checkArgument(minDelay >= 0, "Please only pass positive delays");
        Preconditions.checkArgument(maxDelay >= 0, "Please only pass positive delays");

        for (char element : text.toCharArray()) {
            dispatchPressKey(element, Random.nextInt(minDelay, maxDelay));
        }

        if (pressEnter) {
            pressEnter();
        }
    }

    private synchronized void dispatchPressKey(int eventKey, int millis) {
        dispatch(new KeyEvent(component, KeyEvent.KEY_PRESSED, System.currentTimeMillis() + millis, 0, eventKey, (char) eventKey, KeyEvent.KEY_LOCATION_STANDARD));
        dispatch(new KeyEvent(component, KeyEvent.KEY_RELEASED, System.currentTimeMillis() + millis, 0, eventKey, (char) eventKey, KeyEvent.KEY_LOCATION_STANDARD));
    }

    public synchronized void dispatchTypeKey(char c, int delay) {
        AWTKeyStroke keystroke = AWTKeyStroke.getAWTKeyStroke(c);
        int keycode = keystroke.getKeyCode();
        if (c >= 'a' && c <= 'z') keycode -= 32;
        dispatch(new KeyEvent(component, KeyEvent.KEY_PRESSED, System.currentTimeMillis() + delay, keystroke.getModifiers(), keystroke.getKeyCode(), keystroke.getKeyChar(), KeyEvent.KEY_LOCATION_STANDARD));
        if (!(keycode >= KeyEvent.VK_LEFT && keycode <= KeyEvent.VK_DOWN)) {
            dispatch(generateKeyEvent(c, KeyEvent.KEY_TYPED, 0));
        }
    }

    private KeyEvent generateKeyEvent(char key, int type, int wait) {
        AWTKeyStroke ks = AWTKeyStroke.getAWTKeyStroke(key);
        return new KeyEvent(component, type, System.currentTimeMillis() + wait, ks.getModifiers(), ks.getKeyCode(), ks.getKeyChar());
    }


    public void dispatch(KeyEvent event) {
        Preconditions.checkNotNull(event);
        component.dispatchEvent(event);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        Events.getAcuityEventBus().post(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'a'){
            TileDumper.execute();
        }
        if (e.getKeyChar() == 'c'){
            TileDumper.clear();
        }
        Events.getAcuityEventBus().post(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Events.getAcuityEventBus().post(e);
    }
}
