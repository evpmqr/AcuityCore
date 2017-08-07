package com.acuity.web.site.events;

import com.acuity.db.arango.monitor.ArangoMonitorStream;
import com.acuity.db.arango.monitor.events.ArangoEvent;
import com.acuity.db.arango.monitor.events.ArangoEventImpl;
import com.acuity.db.arango.monitor.events.wrapped.impl.BotClientEvent;
import com.acuity.db.arango.monitor.events.wrapped.impl.RSAccountEvent;
import com.acuity.db.services.impl.BotClientService;
import com.acuity.db.util.DBAccess;
import com.acuity.web.site.DashboardUI;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.eventbus.SubscriberExceptionHandler;

/**
 * Created by Zachary Herridge on 8/1/2017.
 */
public class Events implements SubscriberExceptionHandler {

    private static ArangoMonitorStream arangoMonitor = new ArangoMonitorStream("http://AcuityBotting.com:8529", "AcuityCore-Prod", DBAccess.getUsername(), DBAccess.getPassword());
    private static EventBus dbEventBus = new EventBus(new SubscriberExceptionHandler() {
        @Override
        public void handleException(Throwable throwable, SubscriberExceptionContext subscriberExceptionContext) {
            throwable.printStackTrace();
        }
    });

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

    public static EventBus getDBEventBus() {
        return dbEventBus;
    }

    public static void start(){
        arangoMonitor.addListener(new ArangoListener());
        arangoMonitor.start();
    }

    public static void stop(){
        arangoMonitor.stop();
    }

    @Override
    public final void handleException(final Throwable exception, final SubscriberExceptionContext context) {
        exception.printStackTrace();
    }

    public static class ArangoListener implements ArangoMonitorStream.ArangoStreamListener{

        @Override
        public void onEvent(ArangoEventImpl event) {
            if (event.getType() == ArangoEvent.CREATE_OR_UPDATE || event.getType() == ArangoEvent.DELETE){
                if (BotClientService.getInstance().getCollectionID().equals(event.getCid())){
                    dbEventBus.post(new BotClientEvent(event));
                }
                else if (event.getCName().equals("RSAccount")){
                    dbEventBus.post(new RSAccountEvent(event));
                }
                else {
                    dbEventBus.post(event);
                }
            }
        }
    }
}
