package com.acuity.web.site.views;

import com.acuity.web.site.views.impl.dashboard.botclient.BotClientView;
import com.acuity.web.site.views.impl.dashboard.botclient.BotClientsListView;
import com.acuity.web.site.views.impl.dashboard.menu.MenuItem;
import com.acuity.web.site.views.impl.dashboard.rs.account.RSAccountView;
import com.acuity.web.site.views.impl.dashboard.rs.account.RSAccountsListView;

/**
 * Created by Zach on 8/5/2017.
 */
public enum View {
    ACCOUNTS(RSAccountsListView.class, "RS-Accounts", true),
    ACCOUNT(RSAccountView.class, "RS-Account", false),
    CLIENTS(BotClientsListView.class, "Clients", true),
    CLIENT(BotClientView.class, "Client", false)
    ;

    private Class<? extends com.vaadin.navigator.View> viewClass;
    private String name;
    private boolean navBar;

    View(Class<? extends com.vaadin.navigator.View> viewClass, String name, boolean navBar) {
        this.viewClass = viewClass;
        this.name = name;
        this.navBar = navBar;
    }

    public Class<? extends com.vaadin.navigator.View> getViewClass() {
        return viewClass;
    }

    public String getName() {
        return name;
    }

    public MenuItem createMenuItem(){
        return new MenuItem(this);
    }

    public boolean isNavBar() {
        return navBar;
    }
}
