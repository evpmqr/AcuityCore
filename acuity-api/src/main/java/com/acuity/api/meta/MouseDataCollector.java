package com.acuity.api.meta;

import com.acuity.api.Events;
import com.google.common.eventbus.Subscribe;

import java.awt.event.MouseEvent;

/**
 * Created by Zachary Herridge on 7/6/2017.
 */
public class MouseDataCollector {

    public static final MouseDataCollector INSTANCE = new MouseDataCollector();

    @Subscribe
    public void processCanvasEvent(MouseEvent mouseEvent){

    }

    public void start(){
        Events.getRsEventBus().register(this);
        Events.getAcuityEventBus().register(this);
    }

    public void stop(){
        Events.getRsEventBus().unregister(this);
        Events.getAcuityEventBus().unregister(this);
    }
}
