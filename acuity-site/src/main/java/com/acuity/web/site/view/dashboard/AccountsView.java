package com.acuity.web.site.view.dashboard;

import com.acuity.db.arango.monitor.events.ArangoEventImpl;
import com.acuity.web.site.events.Events;
import com.google.common.eventbus.Subscribe;
import com.vaadin.navigator.View;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by Zachary Herridge on 8/1/2017.
 */

public class AccountsView extends VerticalLayout implements View{

    private Label countingLbl = new Label("Count = 0");

    public AccountsView() {
        addComponent(countingLbl);
        Events.getDBEventBus().register(this);
    }

    @Override
    public void detach() {
        Events.getDBEventBus().unregister(this);
        super.detach();
    }

    @Subscribe
    public void onArangoEvent(ArangoEventImpl event){
        if (event.getType() == 2300){
            getUI().access(() -> countingLbl.setValue(event.toString()));
        }
    }
}
