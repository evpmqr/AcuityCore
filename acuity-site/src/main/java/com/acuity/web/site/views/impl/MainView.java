package com.acuity.web.site.views.impl;

import com.acuity.web.site.DashboardNavigator;
import com.acuity.web.site.views.impl.dashboard.menu.Menu;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by Zachary Herridge on 8/1/2017.
 */
public class MainView extends HorizontalLayout {

    private ComponentContainer content = new CssLayout();
    private DashboardNavigator dashboardNavigator;

    public MainView() {
        setSizeFull();
        addStyleName("mainview");
        setSpacing(false);

        addComponent(new Menu());

        VerticalLayout body = new VerticalLayout();
        addComponent(body);
        body.setMargin(false);
        setExpandRatio(body, 1.0f);


        HorizontalLayout panel = new HorizontalLayout();
        panel.addStyleName("acuity-primary-bar");
        panel.setMargin(false);
        panel.setHeight(43, Unit.PIXELS);

        body.addComponent(panel);

        content.addStyleName("smallMargin");
        content.addStyleName("view-content");
        content.setSizeFull();
        body.addComponent(content);
        body.setExpandRatio(content, 1.0f);

        dashboardNavigator = new DashboardNavigator(content);
    }

    public DashboardNavigator getDashboardNavigator() {
        return dashboardNavigator;
    }
}
