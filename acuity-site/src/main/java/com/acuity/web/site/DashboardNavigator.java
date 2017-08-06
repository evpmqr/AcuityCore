package com.acuity.web.site;

import com.acuity.web.site.events.DashboardEvent;
import com.acuity.web.site.events.Events;
import com.acuity.web.site.view.ErrorView;
import com.acuity.web.site.views.View;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewProvider;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;

/**
 * Created by Zachary Herridge on 8/1/2017.
 */
public class DashboardNavigator extends Navigator {

    public DashboardNavigator(ComponentContainer container) {
        super(UI.getCurrent(), container);

        initViewProviders();

        addViewChangeListener(viewChangeEvent -> {
            Events.post(new DashboardEvent.ViewChange(viewChangeEvent.getViewName()));
            Events.post(new DashboardEvent.BrowserResizeEvent());
            Events.post(new DashboardEvent.CloseOpenWindowsEvent());
            return true;
        });
    }

    private void initViewProviders() {
        for (View view : View.values()) {
            ViewProvider viewProvider = new ClassBasedViewProvider(view.getName(), view.getViewClass());
            addProvider(viewProvider);
        }

        setErrorProvider(new ViewProvider() {
            @Override
            public String getViewName(String s) {
                return "ERROR VIEW";
            }

            @Override
            public com.vaadin.navigator.View getView(String s) {
                return new ErrorView();
            }
        });

    }
}
