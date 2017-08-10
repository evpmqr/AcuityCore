package com.acuity.db.arango.monitor.events;

import com.acuity.db.arango.monitor.ArangoMonitorStream;
import com.acuity.db.arango.monitor.events.wrapped.impl.MessagePackageEvent;
import com.acuity.db.arango.monitor.events.wrapped.impl.RSAccountEvent;
import com.acuity.db.arango.monitor.events.wrapped.impl.bot.client.id_events.impl.BotClientConfigEvent;
import com.acuity.db.arango.monitor.events.wrapped.impl.bot.client.id_events.impl.BotClientEvent;
import com.acuity.db.arango.monitor.events.wrapped.impl.bot.client.id_events.impl.RSAccountAssignedToEvent;
import com.acuity.db.services.impl.BotClientService;
import com.acuity.security.DBAccess;
import com.google.common.eventbus.EventBus;

/**
 * Created by Zachary Herridge on 8/9/2017.
 */
public class ArangoEventDispatcher {

    private ArangoMonitorStream arangoMonitor = new ArangoMonitorStream("http://AcuityBotting.com:8529", "AcuityCore-Prod", DBAccess.getUsername(), DBAccess.getPassword());
    private EventBus eventBus = new EventBus((throwable, subscriberExceptionContext) -> throwable.printStackTrace());

    public void start(){
        arangoMonitor.addListener(new ArangoListener());
        arangoMonitor.start();
    }

    public void stop() {
        arangoMonitor.stop();
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    private class ArangoListener implements ArangoMonitorStream.ArangoStreamListener{

        @Override
        public void onEvent(ArangoEventImpl event) {
            if (event.getType() == ArangoEvent.CREATE_OR_UPDATE || event.getType() == ArangoEvent.DELETE){
                if (BotClientService.getInstance().getCollectionID().equals(event.getCid())){
                    eventBus.post(new BotClientEvent(event));
                }
                else if (event.getCName().equals("RSAccount")){
                    eventBus.post(new RSAccountEvent(event));
                }
                else if (event.getCName().equals("BotClientConfig")){
                    eventBus.post(new BotClientConfigEvent(event));
                }
                else if (event.getCName().equals("AssignedTo")){
                    eventBus.post(new RSAccountAssignedToEvent(event));
                }
                else if (event.getCName().equals("MessagePackage")){
                    eventBus.post(new MessagePackageEvent(event));
                }
                else {
                    eventBus.post(event);
                }
            }
        }
    }

}


