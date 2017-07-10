package com.acuity.api.rs.wrappers.common.locations.screen;

import java.awt.*;

/**
 * Created by Zachary Herridge on 7/5/2017.
 */
public class ScreenLocation {

    protected int x, y;

    public ScreenLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void increment(int xAmount, int yAmount){
        this.x += xAmount;
        this.y += yAmount;
    }

    public Point toPoint(){
        return new Point(x, y);
    }
}
