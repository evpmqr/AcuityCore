package com.acuity.web.site.views.impl.dashboard.menu;

import com.acuity.db.domain.vertex.impl.AcuityAccount;
import com.acuity.web.site.events.DashboardEvent;
import com.acuity.web.site.events.Events;
import com.acuity.web.site.views.View;
import com.google.common.eventbus.Subscribe;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by Zachary Herridge on 8/1/2017.
 */
public class Menu extends CustomComponent {

    public static final String ID = "dashboard-menu";
    public static final String REPORTS_BADGE_ID = "dashboard-menu-reports-badge";
    public static final String NOTIFICATIONS_BADGE_ID = "dashboard-menu-notifications-badge";
    private static final String STYLE_VISIBLE = "valo-menu-visible";

    private AcuityAccount acuityAccount = VaadinSession.getCurrent().getAttribute(AcuityAccount.class);

    private Label notificationsBadge;
    private Label reportsBadge;
    private MenuBar.MenuItem settingsItem;

    private CssLayout menuItemsLayout = new CssLayout();

    public Menu() {
        setPrimaryStyleName("valo-menu");
        setId(ID);
        setSizeUndefined();


        Events.register(this);

        setCompositionRoot(buildContent());
    }

    private Component buildContent() {
        final CssLayout menuContent = new CssLayout();
        menuContent.addStyleName("sidebar");
        menuContent.addStyleName(ValoTheme.MENU_PART);
        menuContent.addStyleName("no-vertical-drag-hints");
        menuContent.addStyleName("no-horizontal-drag-hints");
        menuContent.setWidth(null);
        menuContent.setHeight("100%");

        menuContent.addComponent(buildTitle());
        menuContent.addComponent(buildToggleButton());
        menuContent.addComponent(menuItemsLayout);
        menuItemsLayout.addStyleName("valo-menuitems");
        buildMenuItems();

        return menuContent;
    }

    private Component buildTitle() {
        Label logo = new Label("AcuityBotting <strong>Dashboard</strong>", ContentMode.HTML);
        logo.setSizeUndefined();
        HorizontalLayout logoWrapper = new HorizontalLayout(logo);
        logoWrapper.setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
        logoWrapper.addStyleName("valo-menu-title");
        logoWrapper.setSpacing(false);
        return logoWrapper;
    }

    private Component buildToggleButton() {
        Button valoMenuToggleButton = new Button("Menu", clickEvent -> {
            if (getCompositionRoot().getStyleName().contains(STYLE_VISIBLE)) {
                getCompositionRoot().removeStyleName(STYLE_VISIBLE);
            } else {
                getCompositionRoot().addStyleName(STYLE_VISIBLE);
            }
        });
        valoMenuToggleButton.setIcon(VaadinIcons.LIST);
        valoMenuToggleButton.addStyleName("valo-menu-toggle");
        valoMenuToggleButton.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        valoMenuToggleButton.addStyleName(ValoTheme.BUTTON_SMALL);
        return valoMenuToggleButton;
    }

    @Subscribe
    public void userLoginRequested(final DashboardEvent.UserLoginRequestedEvent event) {
        acuityAccount = VaadinSession.getCurrent().getAttribute(AcuityAccount.class);
        buildMenuItems();
    }


    private void buildMenuItems() {
        menuItemsLayout.removeAllComponents();
        for (View view : View.values()) {
            if (view.isNavBar() && view.isAccessible(acuityAccount)){
                menuItemsLayout.addComponent(view.createMenuItem());
            }
        }
    }
}


