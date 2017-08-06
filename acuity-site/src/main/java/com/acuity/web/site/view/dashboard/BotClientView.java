package com.acuity.web.site.view.dashboard;

import com.acuity.db.arango.monitor.events.ArangoEvent;
import com.acuity.db.arango.monitor.events.wrapped.impl.BotClientEvent;
import com.acuity.db.domain.vertex.impl.AcuityAccount;
import com.acuity.db.domain.vertex.impl.botclient.BotClient;
import com.acuity.db.services.impl.BotClientService;
import com.acuity.web.site.events.Events;
import com.google.common.eventbus.Subscribe;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by Zach on 8/4/2017.
 */
public class BotClientView extends VerticalLayout implements View {

    private AcuityAccount acuityAccount = VaadinSession.getCurrent().getAttribute(AcuityAccount.class);
    private BotClient botClient;


    private void build(){
        addComponent(new Label("ClientKey: " + botClient.getKey()));
        addComponent(new Button("Kill Bot", clickEvent -> {

        }));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        BotClientService.getInstance().getByKey(event.getParameters()).ifPresent(result -> {
            if (result.getOwnerID().equals(acuityAccount.getKey())){
                botClient = result;
                Events.getDBEventBus().register(this);
            }
        });
        if (botClient == null) getUI().getNavigator().navigateTo("ERROR VIEW");
        else build();
    }

    @Override
    public void detach() {
        Events.getDBEventBus().unregister(this);
        super.detach();
    }

    @Subscribe
    public void onBotClientEvent(BotClientEvent event) {
        if (event.getBotClient().getKey().equals(botClient.getKey())){
            if (event.getType() == ArangoEvent.DELETE) {

            }
            else if (event.getBotClient().getOwnerID().equals(acuityAccount.getKey())) {

            }
        }
    }
}
