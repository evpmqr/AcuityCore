package com.acuity.api.rs.events.impl;

import com.acuity.api.rs.events.RSEvent;

/**
 * Created by Zachary Herridge on 7/6/2017.
 */
public class MouseRecorderUpdateEvent implements RSEvent{

    private final long timeMillis;
    private final int lastX;
    private final int lastY;

    public MouseRecorderUpdateEvent(long timeMillis, int lastX, int lastY) {
        this.timeMillis = timeMillis;
        this.lastX = lastX;
        this.lastY = lastY;
    }

    public long getTimeMillis() {
        return timeMillis;
    }

    public int getLastX() {
        return lastX;
    }

    public int getLastY() {
        return lastY;
    }
}
