package com.acuity.web.site.views.impl.dashboard.script;

import com.acuity.db.domain.vertex.impl.AcuityAccount;
import com.acuity.db.domain.vertex.impl.scripts.Script;
import com.acuity.db.services.impl.ScriptService;
import com.acuity.web.site.components.InlineLabel;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by Zachary Herridge on 8/7/2017.
 */
public class ScriptView extends VerticalLayout implements View {

    private AcuityAccount acuityAccount = VaadinSession.getCurrent().getAttribute(AcuityAccount.class);
    private Script script;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        script = ScriptService.getInstance().getByKey(event.getParameters()).orElse(null);
        if (script == null) getUI().getNavigator().navigateTo(com.acuity.web.site.views.View.ERROR);
        else buildComponent();
    }

    private void buildComponent(){
        addStyleName("view");

        addComponent(createInfoPanel());
    }

    private Panel createInfoPanel(){
        Panel panel = new Panel("<strong>Information</strong>");
        panel.setResponsive(true);
        panel.setCaptionAsHtml(true);

        GridLayout content = new GridLayout(2, 2);
        content.setResponsive(true);
        content.setSpacing(true);
        content.addStyleName("view-top");

        content.addComponents(new InlineLabel("Title:", VaadinIcons.TEXT_LABEL), new Label(script.getTitle()));
        content.addComponents(new InlineLabel("Category:", VaadinIcons.FILE_TREE_SMALL), new Label(script.getCategory()));

        panel.setContent(content);
        return panel;
    }

}
