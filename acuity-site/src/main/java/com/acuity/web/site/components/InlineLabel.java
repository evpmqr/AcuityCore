package com.acuity.web.site.components;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Label;

/**
 * Created by Zachary Herridge on 8/8/2017.
 */
public class InlineLabel extends Label {

    public InlineLabel(String text, VaadinIcons timer) {
        super(timer.getHtml() + " " + text, ContentMode.HTML);
    }
}
