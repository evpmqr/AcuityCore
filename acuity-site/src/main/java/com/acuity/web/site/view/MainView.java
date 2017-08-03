package com.acuity.web.site.view;

import com.acuity.web.site.DashboardNavigator;
import com.acuity.web.site.view.dashboard.menu.Menu;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;

/**
 * Created by Zachary Herridge on 8/1/2017.
 */
public class MainView extends HorizontalLayout {


    public MainView() {
        setSizeFull();
        addStyleName("mainview");
        setSpacing(false);

        addComponent(new Menu());

        ComponentContainer content = new CssLayout();
        content.addStyleName("view-content");
        content.setSizeFull();
        addComponent(content);
        setExpandRatio(content, 1.0f);

        new DashboardNavigator(content);
    }
}
