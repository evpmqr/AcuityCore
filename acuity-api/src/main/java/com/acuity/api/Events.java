package com.acuity.api;

import com.google.common.eventbus.EventBus;

/**
 * Created by Zach on 6/17/2017.
 */
public class Events {

    private static EventBus rsEventBus = new EventBus();
    private static EventBus acuityEventBus = new EventBus();

    public static EventBus getRsEventBus(){
        return rsEventBus;
    }

    public static EventBus getAcuityEventBus() {
        return acuityEventBus;
    }
}
