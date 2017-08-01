package com.acuity.web.site;

import com.acuity.web.site.view.LoginView;
import com.vaadin.annotations.Title;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

import java.util.Locale;

/**
 * Created by Zach on 7/31/2017.
 */
@Title("Acuity Botting")
public class AcuityUI extends UI {
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setLocale(Locale.US);
        Responsive.makeResponsive(this);
        addStyleName(ValoTheme.UI_WITH_MENU);
        setContent(new LoginView());
    }
}
