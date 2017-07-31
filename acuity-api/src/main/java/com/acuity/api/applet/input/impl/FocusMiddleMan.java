package com.acuity.api.applet.input.impl;

import com.acuity.api.Events;
import com.acuity.api.applet.input.InputMiddleMan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Arrays;

/**
 * Created by Zachary Herridge on 7/27/2017.
 */
public class FocusMiddleMan implements InputMiddleMan {

    private static Logger logger = LoggerFactory.getLogger(FocusMiddleMan.class);

    private boolean acceptingUserInput = true;
    private MFocusListener output = new MFocusListener();

    @Override
    public boolean insertInto(Component component) {
        FocusListener[] focusListeners = component.getFocusListeners();
        for (FocusListener focusListener : focusListeners) {
            component.removeFocusListener(focusListener);
            logger.debug("Removed FocusListener {} from component.", focusListener);
        }
        output.setFocusListeners(focusListeners);

        component.addFocusListener(output);
        logger.debug("Added MFocusListener as FocusListener to component {}.", component);

        logger.info("Successfully middle-manned focus listener of component {} with {}.", component, output);
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

    public MFocusListener getOutput() {
        return output;
    }

    public class MFocusListener implements FocusListener {

        private  FocusListener[] focusListeners;

        public void dispatch(FocusEvent e){
            switch (e.getID()){
                case FocusEvent.FOCUS_GAINED:
                    for (FocusListener focusListener : focusListeners) focusListener.focusGained(e);
                    break;
                case FocusEvent.FOCUS_LOST:
                    for (FocusListener focusListener : focusListeners) focusListener.focusLost(e);
                    break;
                default:
                    logger.error("Failed to dispatch unknown FocusEvent {}.", e);
            }
        }

        @Override
        public void focusGained(FocusEvent e) {
            Events.getAcuityEventBus().post(e);
            if (isAcceptingUserInput()) dispatch(e);
        }

        @Override
        public void focusLost(FocusEvent e) {
            Events.getAcuityEventBus().post(e);
            if (isAcceptingUserInput()) dispatch(e);
        }

        public void setFocusListeners(FocusListener[] focusListeners) {
            this.focusListeners = focusListeners;
        }

        @Override
        public String toString() {
            return "MFocusListener{" +
                    "focusListeners=" + Arrays.toString(focusListeners) +
                    '}';
        }
    }

}
