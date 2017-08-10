package com.acuity.web.site.components;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

import java.awt.*;


/**
 * Created by Zachary Herridge on 8/8/2017.
 */
public class OpenButton extends Button {

    public OpenButton(String navigationLocation) throws HeadlessException {
        super(VaadinIcons.ARROW_CIRCLE_UP);
        addStyleName("acuity-primary");
        addStyleName(ValoTheme.BUTTON_ICON_ONLY);
        addStyleName(ValoTheme.BUTTON_TINY);

        addClickListener(clickEvent -> {
            if (clickEvent.isCtrlKey()){

            }
            else {
                getUI().getNavigator().navigateTo(navigationLocation);
            }
        });
    }
}
