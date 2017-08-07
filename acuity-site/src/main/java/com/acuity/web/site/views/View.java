package com.acuity.web.site.views;

import com.acuity.web.site.views.impl.dashboard.botclient.BotClientView;
import com.acuity.web.site.views.impl.dashboard.botclient.BotClientsListView;
import com.acuity.web.site.views.impl.dashboard.menu.MenuItem;
import com.acuity.web.site.views.impl.dashboard.rs.account.RSAccountView;
import com.acuity.web.site.views.impl.dashboard.rs.account.RSAccountsListView;
import com.acuity.web.site.views.impl.dashboard.script.AddScriptView;
import com.acuity.web.site.views.impl.dashboard.script.ScriptView;
import com.acuity.web.site.views.impl.dashboard.script.ScriptsListView;
import com.vaadin.icons.VaadinIcons;

/**
 * Created by Zach on 8/5/2017.
 */
public enum View {
    ACCOUNTS(RSAccountsListView.class, "RS-Accounts", true, VaadinIcons.USER),
    ACCOUNT(RSAccountView.class, "RS-Account", false, null),
    CLIENTS(BotClientsListView.class, "Clients", true, VaadinIcons.CLUSTER),
    CLIENT(BotClientView.class, "Client", false, null),
    ADD_SCRIPT(AddScriptView.class, "AddScript", false, null),
    SCRIPT(ScriptView.class, "Script", false, null),
    SCRIPTS(ScriptsListView.class, "Scripts", true, VaadinIcons.FILE_CODE)
    ;

    private Class<? extends com.vaadin.navigator.View> viewClass;
    private String name;
    private boolean navBar;
    private VaadinIcons icon;

    View(Class<? extends com.vaadin.navigator.View> viewClass, String name, boolean navBar, VaadinIcons icon) {
        this.viewClass = viewClass;
        this.name = name;
        this.navBar = navBar;
        this.icon = icon;
    }

    public Class<? extends com.vaadin.navigator.View> getViewClass() {
        return viewClass;
    }

    public String getName() {
        return name;
    }

    public MenuItem createMenuItem(){
        return new MenuItem(this, icon);
    }

    public boolean isNavBar() {
        return navBar;
    }
}
