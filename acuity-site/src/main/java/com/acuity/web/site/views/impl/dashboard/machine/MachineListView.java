package com.acuity.web.site.views.impl.dashboard.machine;

import com.acuity.db.domain.vertex.impl.AcuityAccount;
import com.acuity.db.domain.vertex.impl.Machine;
import com.acuity.db.services.impl.MachineService;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import java.util.List;

/**
 * Created by Zachary Herridge on 8/10/2017.
 */
public class MachineListView extends VerticalLayout implements View{

    private AcuityAccount acuityAccount = VaadinSession.getCurrent().getAttribute(AcuityAccount.class);

    private List<Machine> machines;
    private Grid<Machine> grid = new Grid<>();

    public MachineListView() {
        machines = MachineService.getInstance().getByOwner(acuityAccount.getID());
        buildComponent();
    }

    private void buildComponent(){
        addStyleName("view");
        buildGrid();
    }

    private void buildGrid(){
        grid.setDataProvider(DataProvider.ofCollection(machines));
        grid.addColumn(machine -> machine.getProperties().getOrDefault("user.name", "")).setCaption("Username");
        grid.addColumn(machine -> machine.getProperties().getOrDefault("os.name", "")).setCaption("OS");
        grid.addColumn(machine -> machine.getProperties().getOrDefault("os.arch", "")).setCaption("Arch");
        grid.addColumn(machine -> machine.getProperties().getOrDefault("user.country", "")).setCaption("Country");

        grid.addComponentColumn(machine -> {
            HorizontalLayout content = new HorizontalLayout();

            Button remove = new Button(VaadinIcons.MINUS_CIRCLE);
            remove.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
            remove.addStyleName(ValoTheme.BUTTON_TINY);
            remove.addStyleName(ValoTheme.BUTTON_DANGER);
            remove.addClickListener(clickEvent -> {
                MachineService.getInstance().getCollection().deleteDocument(machine.getKey());
                machines.removeIf(machine1 -> machine1.getKey().equals(machine.getKey()));
                grid.getDataProvider().refreshAll();
            });
            content.addComponent(remove);
            return content;
        }).setCaption("Actions").setSortable(false);

        grid.setColumnReorderingAllowed(true);
        grid.setSizeFull();
        grid.getColumns().forEach(column -> column.setHidable(true));
        addComponent(grid);
    }

}
