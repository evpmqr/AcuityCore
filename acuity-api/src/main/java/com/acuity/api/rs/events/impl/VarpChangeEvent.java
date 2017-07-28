package com.acuity.api.rs.events.impl;

import com.acuity.api.rs.events.RSEvent;

/**
 * Created by Zachary Herridge on 7/28/2017.
 */
public class VarpChangeEvent implements RSEvent {
    private int index;

    public VarpChangeEvent(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
