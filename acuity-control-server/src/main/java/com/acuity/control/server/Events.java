package com.acuity.control.server;

import com.acuity.db.arango.monitor.ArangoMonitorStream;
import com.acuity.db.arango.monitor.events.ArangoEvent;
import com.acuity.db.arango.monitor.events.ArangoEventImpl;
import com.acuity.db.arango.monitor.events.wrapped.impl.BotClientEvent;
import com.acuity.db.arango.monitor.events.wrapped.impl.MessagePackageEvent;
import com.acuity.db.arango.monitor.events.wrapped.impl.RSAccountAssignedToEvent;
import com.acuity.db.services.impl.BotClientService;
import com.acuity.db.services.impl.MessagePackageService;
import com.acuity.db.util.DBAccess;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.eventbus.SubscriberExceptionHandler;

/**
 * Created by Zachary Herridge on 8/4/2017.
 */
public class Events {

    private static ArangoMonitorStream arangoMonitor = new ArangoMonitorStream("http://AcuityBotting.com:8529", "AcuityCore-Prod", DBAccess.getUsername(), DBAccess.getPassword());

    private static EventBus dbEventBus = new EventBus(new SubscriberExceptionHandler() {
        @Override
        public void handleException(Throwable throwable, SubscriberExceptionContext subscriberExceptionContext) {
            throwable.printStackTrace();
        }
    });

    public static void start(){
        arangoMonitor.addListener(new ArangoListener());
        arangoMonitor.start();
    }

    public static void stop(){
        arangoMonitor.stop();
    }

    public static EventBus getDBEventBus() {
        return dbEventBus;
    }

    public static class ArangoListener implements ArangoMonitorStream.ArangoStreamListener{

        @Override
        public void onEvent(ArangoEventImpl event) {
            if (event.getType() == ArangoEvent.CREATE_OR_UPDATE || event.getType() == ArangoEvent.DELETE){
                if (MessagePackageService.getInstance().getCollectionID().equals(event.getCid())){
                    dbEventBus.post(new MessagePackageEvent(event));
                }
                else if (BotClientService.getInstance().getCollectionID().equals(event.getCid())){
                    dbEventBus.post(new BotClientEvent(event));
                }
                else if (event.getCName().equals("AssignedTo")){
                    dbEventBus.post(new RSAccountAssignedToEvent(event));
                }
                else {
                    dbEventBus.post(event);
                }
            }
        }
    }
}
