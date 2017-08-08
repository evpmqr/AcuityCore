package com.acuity.web.site.views.impl.dashboard.rs.account;

import com.acuity.db.arango.monitor.events.wrapped.impl.RSAccountEvent;
import com.acuity.db.domain.vertex.impl.AcuityAccount;
import com.acuity.db.domain.vertex.impl.RSAccount;
import com.acuity.db.services.impl.RSAccountService;
import com.acuity.web.site.components.InlineLabel;
import com.acuity.web.site.events.Events;
import com.google.common.base.Strings;
import com.google.common.eventbus.Subscribe;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by Zachary Herridge on 8/7/2017.
 */
public class RSAccountView extends VerticalLayout implements View{

    private AcuityAccount acuityAccount = VaadinSession.getCurrent().getAttribute(AcuityAccount.class);
    private RSAccount rsAccount;

    public void build(){
        addStyleName("view");
        Events.getDBEventBus().register(this);

        addInformationPanel();
    }

    private void addInformationPanel() {
        Panel panel = new Panel("<strong>Information</strong>");
        panel.setResponsive(true);
        panel.setCaptionAsHtml(true);

        GridLayout content = new GridLayout(2, 3);
        content.setResponsive(true);
        content.setSpacing(true);
        content.addStyleName("view-top");


        content.addComponent(new InlineLabel("Email:", VaadinIcons.MAILBOX));
        content.addComponent(new Label(rsAccount.getEmail()));

        content.addComponent(new InlineLabel("IGN:", VaadinIcons.CHAT));
        content.addComponent(new Label(rsAccount.getIgn()));

        content.addComponent(new InlineLabel("Password:", VaadinIcons.PASSWORD));


        Button passwordBttn = new Button(Strings.repeat("*", rsAccount.getPassword().length()));
        passwordBttn.setStyleName(ValoTheme.BUTTON_BORDERLESS);
        passwordBttn.addStyleName("grid-button");
        passwordBttn.setHeight(25, Unit.PIXELS);
        passwordBttn.addClickListener(clickEvent -> {
            passwordBttn.setCaption(passwordBttn.getCaption().equals(rsAccount.getPassword()) ? Strings.repeat("*", rsAccount.getPassword().length()) : rsAccount.getPassword());
        });
        content.addComponent(passwordBttn);


        panel.setContent(content);
        addComponent(panel);
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
