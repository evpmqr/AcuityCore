package com.acuity.web.site.views.impl.dashboard.script;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by Zachary Herridge on 8/7/2017.
 */
public class ScriptView extends VerticalLayout implements View {

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        addStyleName("view");
    }
}
