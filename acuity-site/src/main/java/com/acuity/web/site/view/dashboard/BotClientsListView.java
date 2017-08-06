package com.acuity.web.site.view.dashboard;

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
    private List<BotClient> botClients = new ArrayList<>();

    private Grid<BotClient> grid = new Grid<>();

    public BotClientsListView() {
        setSizeFull();
        botClients = BotClientService.getInstance().getByOwnerKey(acuityAccount.getKey());
        Events.getDBEventBus().register(this);

        buildActions();
        buildGrid();
    }

    private void buildActions(){

    }

    private void buildGrid(){
        MultiSelectionModel<BotClient> selectionModel = (MultiSelectionModel<BotClient>) grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.setDataProvider(DataProvider.ofCollection(botClients));
        grid.addColumn(BotClient::getKey).setCaption("Key");
        grid.addColumn(BotClient::getConnectionTime, new LocalDateTimeRenderer()).setCaption("Connected");
        grid.setSizeFull();
        grid.setColumnReorderingAllowed(true);
        grid.addItemClickListener(itemClick -> {
            UI.getCurrent().getNavigator().navigateTo("Client/" + itemClick.getItem().getKey());
        });
        addComponent(grid);
    }

    @Override
    public void detach() {
        Events.getDBEventBus().unregister(this);
        super.detach();
    }

    @Subscribe
    public void onBotClientEvent(BotClientEvent event) {
        if (event.getType() == ArangoEvent.DELETE) {
            botClients.remove(event.getBotClient());
        }
        else if (event.getBotClient().getOwnerID().equals(acuityAccount.getKey())) {
            botClients.remove(event.getBotClient());
            botClients.add(event.getBotClient());
        }
        grid.getDataProvider().refreshAll();
    }
}
