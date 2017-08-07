package com.acuity.web.site.views.impl.dashboard.script;

import com.acuity.db.domain.vertex.impl.AcuityAccount;
import com.acuity.db.domain.vertex.impl.scripts.Script;
import com.acuity.db.services.impl.ScriptService;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.MultiSelectionModel;

import java.util.List;

/**
 * Created by Zachary Herridge on 8/7/2017.
 */
public class ScriptsListView extends VerticalLayout implements View {

    private AcuityAccount acuityAccount = VaadinSession.getCurrent().getAttribute(AcuityAccount.class);

    private Grid<Script> grid = new Grid<>();
    private MultiSelectionModel<Script> scriptSelectionModel = (MultiSelectionModel<Script>) grid.setSelectionMode(Grid.SelectionMode.MULTI);

    public ScriptsListView() {
        List<Script> scripts = ScriptService.getInstance().getByAccess(acuityAccount.getID(), Script.Access.PUBLIC.getCode(), acuityAccount.getRank());
        grid.setItems(scripts);

        buildGrid();

        Button addScript = new Button("New Script", clickEvent -> {
            getUI().getNavigator().navigateTo(com.acuity.web.site.views.View.ADD_SCRIPT.getName());
        });
        addComponent(addScript);
    }

    private void buildGrid(){
        grid.addColumn(Script::getTitle).setCaption("Title");
        grid.addColumn(script -> script.getAuthor().getDisplayName()).setCaption("Author");
        grid.addColumn(Script::getCategory).setCaption("Category");
        grid.addColumn(Script::getDesc).setCaption("Description");
        grid.setSizeFull();
        grid.setColumnReorderingAllowed(true);
        addComponent(grid);
    }

}
