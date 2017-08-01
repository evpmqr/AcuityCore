package com.acuity.web.site;

import com.acuity.web.site.events.DashboardEvent;
import com.acuity.web.site.events.Events;
import com.acuity.web.site.view.LoginView;
import com.acuity.web.site.view.MainView;
import com.google.common.eventbus.Subscribe;
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
@Title("Acuity Botting")
public class DashboardUI extends UI {

    private boolean loggedIn = false;
    private Events events = new Events();

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