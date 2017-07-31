package com.acuity.api.applet.input.impl;

import com.acuity.api.Events;
import com.acuity.api.applet.input.InputMiddleMan;
import com.acuity.api.rs.utils.Random;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

/**
 * Created by Zach on 6/17/2017.
 */
public class KeyboardMiddleMan implements InputMiddleMan {

    private static Logger logger = LoggerFactory.getLogger(KeyboardMiddleMan.class);

    private Component component;
    private boolean acceptingUserInput = true;
    private MKeyboardListener output = new MKeyboardListener();

    public synchronized void pressEnter() {
        dispatchPressKey(KeyEvent.VK_ENTER, 20);
    }

    public synchronized void sendText(String text, boolean pressEnter, int minDelay, int maxDelay) {
        Preconditions.checkNotNull(text);
        Preconditions.checkArgument(minDelay >= 0, "Invalid delay - Negative min delay.");
        Preconditions.checkArgument(maxDelay >= 0, "Invalid delay - Negative max delay.");

        for (char element : text.toCharArray()) {
            dispatchPressKey(element, Random.nextInt(minDelay, maxDelay));
        }
        if (pressEnter) pressEnter();
    }

    public synchronized void dispatchTypeKey(char c, int delay) {
        AWTKeyStroke keystroke = AWTKeyStroke.getAWTKeyStroke(c);
        int keycode = keystroke.getKeyCode();
        if (c >= 'a' && c <= 'z') keycode -= 32;
        output.dispatch(new KeyEvent(component, KeyEvent.KEY_PRESSED, System.currentTimeMillis() + delay, keystroke.getModifiers(), keystroke.getKeyCode(), keystroke.getKeyChar(), KeyEvent.KEY_LOCATION_STANDARD));
        if (!(keycode >= KeyEvent.VK_LEFT && keycode <= KeyEvent.VK_DOWN)) {
            output.dispatch(generateKeyEvent(c, KeyEvent.KEY_TYPED, 0));
        }
    }

    private synchronized void dispatchPressKey(int eventKey, int millis) {
        output.dispatch(new KeyEvent(component, KeyEvent.KEY_PRESSED, System.currentTimeMillis() + millis, 0, eventKey, (char) eventKey, KeyEvent.KEY_LOCATION_STANDARD));
        output.dispatch(new KeyEvent(component, KeyEvent.KEY_RELEASED, System.currentTimeMillis() + millis, 0, eventKey, (char) eventKey, KeyEvent.KEY_LOCATION_STANDARD));
    }

    private KeyEvent generateKeyEvent(char key, int type, int wait) {
        AWTKeyStroke ks = AWTKeyStroke.getAWTKeyStroke(key);
        return new KeyEvent(component, type, System.currentTimeMillis() + wait, ks.getModifiers(), ks.getKeyCode(), ks.getKeyChar());
    }

    public MKeyboardListener getOutput() {
        return output;
    }

    @Override
    public boolean insertInto(Component component) {
        this.component = component;

        KeyListener[] keyListeners = component.getKeyListeners();
        for (KeyListener keyListener : keyListeners) {
            component.removeKeyListener(keyListener);
            logger.debug("Removed KeyListener {} from component.", keyListener);
        }
        output.setKeyListeners(keyListeners);

        component.addKeyListener(output);
        logger.debug("Added MKeyboardListener as KeyListener to component {}.", component);

        logger.info("Successfully middle-manned keyboard of component {} with {}.", component, output);
        return true;
    }

    @Override
    public boolean isAcceptingUserInput() {
        return acceptingUserInput;
    }

    @Override
    public void setAcceptingUserInput(boolean acceptingUserInput) {
        this.acceptingUserInput = acceptingUserInput;
    }

    public class MKeyboardListener implements KeyListener {

        private KeyListener[] keyListeners;

        public void dispatch(KeyEvent e) {
            if (e.isConsumed()) return;
            switch (e.getID()) {
                case KeyEvent.KEY_PRESSED:
                    for (KeyListener keyListener : keyListeners) keyListener.keyPressed(e);
                    break;
                case KeyEvent.KEY_TYPED:
                    for (KeyListener keyListener : keyListeners) keyListener.keyTyped(e);
                    break;
                case KeyEvent.KEY_RELEASED:
                    for (KeyListener keyListener : keyListeners) keyListener.keyReleased(e);
                    break;
                default:
                    logger.error("Failed to dispatch unknown KeyEvent {}.", e);
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
            Events.getAcuityEventBus().post(e);
            if (isAcceptingUserInput()) dispatch(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            Events.getAcuityEventBus().post(e);
            if (isAcceptingUserInput()) dispatch(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            Events.getAcuityEventBus().post(e);
            if (isAcceptingUserInput()) dispatch(e);
        }

        public void setKeyListeners(KeyListener[] keyListeners) {
            this.keyListeners = keyListeners;
        }

        @Override
        public String toString() {
            return "MKeyboardListener{" +
                    "keyListeners=" + Arrays.toString(keyListeners) +
                    '}';
        }
    }
}
