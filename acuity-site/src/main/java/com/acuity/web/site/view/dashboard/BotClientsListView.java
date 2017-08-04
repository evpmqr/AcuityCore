package com.acuity.web.site.view.dashboard;

import com.acuity.db.arango.monitor.events.ArangoEvent;
import com.acuity.db.arango.monitor.events.wrapped.impl.BotClientEvent;
import com.acuity.db.domain.vertex.impl.AcuityAccount;
import com.acuity.db.domain.vertex.impl.BotClient;
import com.acuity.db.services.impl.BotClientService;
import com.acuity.web.site.events.Events;
import com.google.common.eventbus.Subscribe;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.MultiSelectionModel;
import com.vaadin.ui.renderers.LocalDateTimeRenderer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zachary Herridge on 8/4/2017.
 */
public class BotClientsListView extends VerticalLayout implements View {

    private AcuityAccount acuityAccount = VaadinSession.getCurrent().getAttribute(AcuityAccount.class);
    private Map<String, BotClient> clients = new HashMap<>();

    private Grid<BotClient> grid = new Grid<>();

    public BotClientsListView() {
        BotClientService.getInstance().getByOwnerKey(acuityAccount.getKey()).forEach(botClient -> clients.put(botClient.getKey(), botClient));
        MultiSelectionModel<BotClient> selectionModel = (MultiSelectionModel<BotClient>) grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.setItems(clients.values());
        grid.addColumn(BotClient::getKey).setCaption("Key");
        grid.addColumn(BotClient::getConnectionTime, new LocalDateTimeRenderer()).setCaption("Connected");
        grid.setSizeFull();
        addComponent(grid);
        Events.getDBEventBus().register(this);
    }

    @Override
    public void detach() {
        Events.getDBEventBus().unregister(this);
        super.detach();
    }

    private void updateGrid() {
        getUI().access(() -> {
            grid.setItems(clients.values());
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
