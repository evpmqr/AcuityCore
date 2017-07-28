package com.acuity.api;

import com.google.common.eventbus.EventBus;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Zach on 6/17/2017.
 */
public class Events {

    private static Executor executor = Executors.newSingleThreadExecutor();
    private static EventBus rsEventBus = new EventBus();
    private static EventBus acuityEventBus = new EventBus();

    public static EventBus getRsEventBus(){
        return rsEventBus;
    }

    public static EventBus getAcuityEventBus() {
        return acuityEventBus;
    }

    public static void queuePost(EventBus eventBus, Object post){
        executor.execute(() -> eventBus.post(post));
    }
}
