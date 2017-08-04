package com.acuity.web.site.view.dashboard;

import com.acuity.db.arango.monitor.events.ArangoEvent;
import com.acuity.db.arango.monitor.events.wrapped.impl.BotClientEvent;
import com.acuity.db.domain.vertex.impl.AcuityAccount;
import com.acuity.db.domain.vertex.impl.BotClient;
import com.acuity.web.site.events.Events;
import com.google.common.eventbus.Subscribe;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zachary Herridge on 8/4/2017.
 */
public class BotClientsListView extends VerticalLayout implements View {

    private AcuityAccount acuityAccount = VaadinSession.getCurrent().getAttribute(AcuityAccount.class);
    private Map<String, BotClient> clients = new HashMap<>();

    public BotClientsListView() {
        Events.getDBEventBus().register(this);
    }

    @Override
    public void detach() {
        Events.getDBEventBus().unregister(this);
        super.detach();
    }

    private void updateGrid() {
        getUI().access(() -> {
            removeAllComponents();
            for (BotClient botClient : clients.values()) {
                addComponent(new Label(botClient.toString()));
            }
        });
    }

    @Subscribe
    public void onBotClientEvent(BotClientEvent event) {
        System.out.println(event);
        if (event.getType() == ArangoEvent.DELETE) {
            if (clients.remove(event.getBotClient().getKey()) != null) updateGrid();
        }
        else if (event.getBotClient().getOwnerID().equals(acuityAccount.getKey())) {
            clients.put(event.getBotClient().getKey(), event.getBotClient());
            updateGrid();
        }

    }
}
