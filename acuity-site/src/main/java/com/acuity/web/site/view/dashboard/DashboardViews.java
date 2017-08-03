package com.acuity.web.site.view.dashboard;

import com.acuity.web.site.view.dashboard.menu.MenuItem;
import com.vaadin.navigator.View;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Zachary Herridge on 8/1/2017.
 */
public class DashboardViews {

    public static List<DashboardView> getViews(){
        return Arrays.asList(new DashboardView(AccountsView.class, "RS Accounts"), new DashboardView(AccountsView.class, "Proxies"));
    }

    public static class DashboardView {

        private Class<? extends View> viewClass;
        private String name;

        public DashboardView(Class<? extends View> viewClass, String name) {
            this.viewClass = viewClass;
            this.name = name;
        }

        public Class<? extends View> getViewClass() {
            return viewClass;
        }

        public String getName() {
            return name;
        }

        public MenuItem createMenuItem(){
            return new MenuItem(this);
        }
    }
}
