package com.acuity.web.site.views.impl.dashboard.rs.account;

import com.acuity.db.arango.monitor.events.ArangoEvent;
import com.acuity.db.arango.monitor.events.wrapped.impl.RSAccountEvent;
import com.acuity.db.domain.vertex.impl.AcuityAccount;
import com.acuity.db.domain.vertex.impl.RSAccount;
import com.acuity.db.services.impl.RSAccountService;
import com.acuity.web.site.components.OpenButton;
import com.acuity.web.site.events.Events;
import com.arangodb.entity.DocumentDeleteEntity;
import com.arangodb.entity.MultiDocumentEntity;
import com.google.common.eventbus.Subscribe;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.MultiSelectionModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zachary Herridge on 8/1/2017.
 */

public class RSAccountsListView extends VerticalLayout implements View{

    private AcuityAccount acuityAccount = VaadinSession.getCurrent().getAttribute(AcuityAccount.class);

    private List<RSAccount> rsAccounts = new ArrayList<>();
    private Grid<RSAccount> grid = new Grid<>();
    private MultiSelectionModel<RSAccount> rsAccountMultiSelectionModel = (MultiSelectionModel<RSAccount>) grid.setSelectionMode(Grid.SelectionMode.MULTI);

    public RSAccountsListView() {
        Events.getDBEventBus().register(this);
        rsAccounts = RSAccountService.getInstance().getByOwner(acuityAccount.getID());
        buildComponent();
    }

    @Subscribe
    public void onRSAccountUpdate(RSAccountEvent event){
        boolean remove = rsAccounts.remove(event.getRsAccount());
        if (event.getType() == ArangoEvent.CREATE_OR_UPDATE && event.getRsAccount().getOwnerID().equals(acuityAccount.getID())) {
            rsAccounts.add(event.getRsAccount());
        }
        grid.getDataProvider().refreshAll();
    }

    private void buildComponent(){
        addStyleName("view");
        setSizeFull();
        addRSAccountsGrid();
        addControls();
    }

    private void addControls(){
        HorizontalLayout controls = new HorizontalLayout();
        controls.addComponents(createNewRSAccountButton(), createDeletedUnusableButton(), createDeleteSelectedButton());
        addComponents(controls);
    }

    private Button createDeleteSelectedButton(){
        Button delete = new Button("Delete Selected", clickEvent -> {
            MultiDocumentEntity<DocumentDeleteEntity<Void>> results = RSAccountService.getInstance().deleteAccounts(rsAccountMultiSelectionModel.getSelectedItems());
            Notification.show("Deleted " + (long) results.getDocuments().size() + " Account(s).", Notification.Type.TRAY_NOTIFICATION);
        });
        delete.setIcon(VaadinIcons.TRASH);
        return delete;
    }

    private Button createNewRSAccountButton(){
        Button addRSAccount = new Button("Add", clickEvent -> {
            final Window window = new Window("Add RS-Account");
            window.setSizeUndefined();
            AddRSAccountForm addRSAccountForm = new AddRSAccountForm(window);
            addRSAccountForm.setMargin(true);
            window.setContent(addRSAccountForm);
            getUI().addWindow(window);
        });
        addRSAccount.setIcon(VaadinIcons.PLUS_CIRCLE);
        return addRSAccount;
    }

    private Button createDeletedUnusableButton() {
        final Button deleteUnusableButton = new Button("Delete Unusable", clickEvent -> {
            RSAccountService.getInstance().deleteUnusableAccounts(acuityAccount.getID());
        });
        deleteUnusableButton.setIcon(VaadinIcons.CLOSE_SMALL);
        return deleteUnusableButton;
    }

    private void addRSAccountsGrid(){
        grid.setDataProvider(DataProvider.ofCollection(rsAccounts));
        grid.addColumn(RSAccount::getEmail).setCaption("Email");
        grid.addColumn(RSAccount::getIgn).setCaption("IGN");
        grid.setSizeFull();
        grid.setColumnReorderingAllowed(true);

        grid.addComponentColumn(account -> {
            HorizontalLayout content = new HorizontalLayout();

            OpenButton openButton = new OpenButton(com.acuity.web.site.views.View.ACCOUNT.getName() + "/" + account.getKey());
            content.addComponent(openButton);

            return content;
        }).setCaption("Actions").setSortable(false);


        grid.getColumns().forEach(column -> column.setHidable(true));
        addComponent(grid);
    }

    @Override
    public void detach() {
        Events.getDBEventBus().unregister(this);
        super.detach();
    }
}
