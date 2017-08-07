package com.acuity.web.site.views;

import com.acuity.web.site.views.impl.dashboard.botclient.BotClientView;
import com.acuity.web.site.views.impl.dashboard.botclient.BotClientsListView;
import com.acuity.web.site.views.impl.dashboard.menu.MenuItem;
import com.acuity.web.site.views.impl.dashboard.rs.account.RSAccountView;
import com.acuity.web.site.views.impl.dashboard.rs.account.RSAccountsListView;
import com.acuity.web.site.views.impl.dashboard.script.AddScriptView;
import com.acuity.web.site.views.impl.dashboard.script.ScriptView;
import com.acuity.web.site.views.impl.dashboard.script.ScriptsListView;

/**
 * Created by Zach on 8/5/2017.
 */
public enum View {
    ACCOUNTS(RSAccountsListView.class, "RS-Accounts", true),
    ACCOUNT(RSAccountView.class, "RS-Account", false),
    CLIENTS(BotClientsListView.class, "Clients", true),
    CLIENT(BotClientView.class, "Client", false),
    ADD_SCRIPT(AddScriptView.class, "AddScript", false),
    SCRIPT(ScriptView.class, "Script", false),
    SCRIPTS(ScriptsListView.class, "Scripts", true)
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
