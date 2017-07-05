package com.acuity.api.applet.input;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by Zach on 6/14/2017.
 */
public class MouseMiddleMan implements MouseListener, MouseMotionListener {

    private static Logger logger = LoggerFactory.getLogger(MouseMiddleMan.class);

    private Component component;
    private MouseListener output;
    private MouseMotionListener outputMotion;

    private Point lastPressedLocation, lastReleasedLocation, lastLocation;

    public void replace(Component component) {
        this.component = component;
        if (component.getMouseListeners().length > 0) {
            output = component.getMouseListeners()[0];
            for (MouseListener mouseListener : component.getMouseListeners()) {
                component.removeMouseListener(mouseListener);
            }
        }

        if (component.getMouseMotionListeners().length > 0) {
            outputMotion = component.getMouseMotionListeners()[0];
            for (MouseMotionListener mouseMotionListener : component.getMouseMotionListeners()) {
                component.removeMouseMotionListener(mouseMotionListener);
            }
        }

        component.addMouseListener(this);
        component.addMouseMotionListener(this);
        logger.info("Replaced mouse of {}.", component);
    }

    public void dispatch(MouseEvent event) {
        Preconditions.checkNotNull(event);
        component.dispatchEvent(event);
    }

    public void dispatchClick(int x, int y, boolean left) {
        dispatch(new MouseEvent(component, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), 0, x, y, 1, false, left ? MouseEvent.BUTTON1 : MouseEvent.BUTTON3));
        dispatch(new MouseEvent(component, MouseEvent.MOUSE_RELEASED, System.currentTimeMillis() + 10, 0, x, y, 1, false, left ? MouseEvent.BUTTON1 : MouseEvent.BUTTON3));
    }

    public void dispatchClick(Point point, boolean left) {
        dispatchClick(point.x, point.x, left);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        output.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        lastPressedLocation = new Point(e.getX(), e.getY());
        output.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        lastReleasedLocation = new Point(e.getX(), e.getY());
        output.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        output.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        output.mouseExited(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        lastLocation = new Point(e.getX(), e.getY());
        outputMotion.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        lastLocation = new Point(e.getX(), e.getY());
        outputMotion.mouseMoved(e);
    }

    public Point getLastPressedLocation() {
        return lastPressedLocation;
    }

    public Point getLastReleasedLocation() {
        return lastReleasedLocation;
    }

    public Point getLastLocation() {
        return lastLocation;
    }
}
