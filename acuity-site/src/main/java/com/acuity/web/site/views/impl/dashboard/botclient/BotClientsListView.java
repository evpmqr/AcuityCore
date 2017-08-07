package com.acuity.web.site.views.impl.dashboard.botclient;

import com.acuity.db.arango.monitor.events.ArangoEvent;
import com.acuity.db.arango.monitor.events.wrapped.impl.BotClientEvent;
import com.acuity.db.domain.vertex.impl.AcuityAccount;
import com.acuity.db.domain.vertex.impl.botclient.BotClient;
import com.acuity.db.services.impl.BotClientService;
import com.acuity.web.site.events.Events;
import com.google.common.eventbus.Subscribe;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.MultiSelectionModel;
import com.vaadin.ui.renderers.LocalDateTimeRenderer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zachary Herridge on 8/4/2017.
 */
public class BotClientsListView extends VerticalLayout implements View {

    private AcuityAccount acuityAccount = VaadinSession.getCurrent().getAttribute(AcuityAccount.class);

    private List<BotClient> clients = new ArrayList<>();
    private Grid<BotClient> clientGrid = new Grid<>();
    private MultiSelectionModel<BotClient> clientSelectionModel = (MultiSelectionModel<BotClient>) clientGrid.setSelectionMode(Grid.SelectionMode.MULTI);

    public BotClientsListView() {
        setSizeFull();
        clients = BotClientService.getInstance().getByOwner(acuityAccount.getID());
        Events.getDBEventBus().register(this);

        buildActions();
        buildGrid();
    }

    private void buildActions(){

    }

    private void buildGrid(){
        clientGrid.setDataProvider(DataProvider.ofCollection(clients));
        clientGrid.addColumn(BotClient::getKey).setCaption("Key");
        clientGrid.addColumn(BotClient::getConnectionTime, new LocalDateTimeRenderer()).setCaption("Connected");
        clientGrid.setSizeFull();
        clientGrid.setColumnReorderingAllowed(true);
        clientGrid.addItemClickListener(itemClick -> {
            UI.getCurrent().getNavigator().navigateTo("Client/" + itemClick.getItem().getKey());
        });
        addComponent(clientGrid);
    }

    @Override
    public void detach() {
        Events.getDBEventBus().unregister(this);
        super.detach();
    }

    @Subscribe
    public void onBotClientEvent(BotClientEvent event) {
        if (event.getType() == ArangoEvent.DELETE) {
            clients.remove(event.getBotClient());
        }
        else if (event.getBotClient().getOwnerID().equals(acuityAccount.getID())) {
            clients.remove(event.getBotClient());
            clients.add(event.getBotClient());
        }
        clientGrid.getDataProvider().refreshAll();
    }
}
