package com.acuity.web.site.views.impl.dashboard.rs.account;

import com.acuity.db.arango.monitor.events.wrapped.impl.RSAccountEvent;
import com.acuity.db.domain.vertex.impl.AcuityAccount;
import com.acuity.db.domain.vertex.impl.RSAccount;
import com.acuity.db.services.impl.RSAccountService;
import com.acuity.web.site.events.Events;
import com.google.common.eventbus.Subscribe;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by Zachary Herridge on 8/7/2017.
 */
public class RSAccountView extends VerticalLayout implements View{

    private AcuityAccount acuityAccount = VaadinSession.getCurrent().getAttribute(AcuityAccount.class);
    private RSAccount rsAccount;

    public void build(){
        Events.getDBEventBus().register(this);
        addComponent(new Label("Email: " + rsAccount.getEmail()));
    }

    @Override
    public void detach() {
        Events.getDBEventBus().unregister(this);
        super.detach();
    }

    @Subscribe
    public void onRSAcccountEvent(RSAccountEvent event){

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        RSAccountService.getInstance().getByKey(event.getParameters()).ifPresent(result -> {
            if (result.getOwnerID().equals(acuityAccount.getID())){
                rsAccount = result;
                Events.getDBEventBus().register(this);
            }
        });
        if (rsAccount == null) getUI().getNavigator().navigateTo("ERROR VIEW");
        else build();
    }
}
