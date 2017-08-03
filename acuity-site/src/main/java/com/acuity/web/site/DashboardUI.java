package com.acuity.web.site;

import com.acuity.db.arango_monitor.ArangoMonitor;
import com.acuity.db.arango_monitor.ArangoMonitorStream;
import com.acuity.db.util.DBAccess;
import com.acuity.web.site.events.DashboardEvent;
import com.acuity.web.site.events.Events;
import com.acuity.web.site.view.LoginView;
import com.acuity.web.site.view.MainView;
import com.google.common.eventbus.Subscribe;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Title;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

import java.util.Locale;

/**
 * Created by Zach on 7/31/2017.
 */
@Push
@Title("Acuity Botting")
public class DashboardUI extends UI {

    private static ArangoMonitor arangoMonitor = new ArangoMonitor(new ArangoMonitorStream("http://127.0.0.1:8529", "_system", DBAccess.getUsername(), DBAccess.getPassword()));

    private boolean loggedIn = false;
    private Events events = new Events();

    public static ArangoMonitor getArangoMonitor() {
        return arangoMonitor;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Events.register(this);

        setLocale(Locale.US);
        Responsive.makeResponsive(this);
        addStyleName(ValoTheme.UI_WITH_MENU);
        updateContent();
        Page.getCurrent().addBrowserWindowResizeListener(browserWindowResizeEvent -> Events.post(new DashboardEvent.BrowserResizeEvent()));
    }

    private void updateContent(){
        if (loggedIn){
            setContent(new MainView());
            getNavigator().navigateTo(getNavigator().getState());
        }
        else {
            setContent(new LoginView());
        }
    }

    @Subscribe
    public void userLoginRequested(final DashboardEvent.UserLoginRequestedEvent event) {
        loggedIn = true;
        updateContent();
    }

    @Subscribe
    public void userLoggedOut(final DashboardEvent.UserLoggedOutEvent event) {
        VaadinSession.getCurrent().close();
        Page.getCurrent().reload();
    }

    @Subscribe
    public void closeOpenWindows(final DashboardEvent.CloseOpenWindowsEvent event) {
        getWindows().forEach(Window::close);
    }

    @Override
    public void detach() {
        Events.unregister(this);
    }

    public static Events getEventBus() {
        return ((DashboardUI) getCurrent()).events;
    }
}
