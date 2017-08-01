package com.acuity.web.site.events;

import com.acuity.web.site.DashboardUI;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.eventbus.SubscriberExceptionHandler;

/**
 * Created by Zachary Herridge on 8/1/2017.
 */
public class Events implements SubscriberExceptionHandler {

    private EventBus eventBus = new EventBus(this);

    public static void post(final Object event) {
        DashboardUI.getEventBus().eventBus.post(event);
    }

    public static void register(final Object object) {
        DashboardUI.getEventBus().eventBus.register(object);
    }

    public static void unregister(final Object object) {
        DashboardUI.getEventBus().eventBus.unregister(object);
    }

    @Override
    public final void handleException(final Throwable exception, final SubscriberExceptionContext context) {
        exception.printStackTrace();
    }
}
