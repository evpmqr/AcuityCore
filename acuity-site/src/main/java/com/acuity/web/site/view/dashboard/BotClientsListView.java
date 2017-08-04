package com.acuity.web.site.view.dashboard;

import com.acuity.db.arango.monitor.ArangoMonitorEvent;
import com.acuity.db.arango.monitor.filters.impl.BotClientFilter;
import com.acuity.db.domain.vertex.impl.AcuityAccount;
import com.acuity.web.site.events.Events;
import com.google.common.eventbus.Subscribe;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by Zachary Herridge on 8/4/2017.
 */
public class BotClientsListView extends VerticalLayout implements View {

    private BotClientFilter botClientFilter = new BotClientFilter(VaadinSession.getCurrent().getAttribute(AcuityAccount.class));

    public BotClientsListView() {
        Events.getDBEventBus().register(this);
    }

    @Override
    public void detach() {
        Events.getDBEventBus().unregister(this);
        super.detach();
    }

    @Subscribe
    public void onArangoEvent(ArangoMonitorEvent event){
        if (event.getType() == 2300){
            botClientFilter.matches(event).ifPresent(botClient -> {
                getUI().access(() -> addComponent(new Label(botClient.toString())));
            });
        }
    }
}
