package com.acuity.web.site.views.impl.dashboard.proxy;

import com.acuity.db.domain.vertex.impl.AcuityAccount;
import com.acuity.db.domain.vertex.impl.Proxy;
import com.acuity.db.services.impl.ProxyService;
import com.google.common.base.Strings;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;

import java.util.List;

/**
 * Created by Zachary Herridge on 8/10/2017.
 */
public class ProxyListView extends VerticalLayout implements View {

    private AcuityAccount acuityAccount = VaadinSession.getCurrent().getAttribute(AcuityAccount.class);

    private List<Proxy> proxies;
    private Grid<Proxy> grid = new Grid<>();

    public ProxyListView() {
        proxies = ProxyService.getInstance().getByOwner(acuityAccount.getID());
        buildComponent();
    }

    private void buildComponent(){
        addStyleName("view");
        buildGrid();
        buildAddProxy();
    }

    private void buildGrid(){
        grid.setDataProvider(DataProvider.ofCollection(proxies));
        grid.addColumn(Proxy::getHost).setCaption("Host");
        grid.addColumn(Proxy::getPort).setCaption("Port");
        grid.addColumn(Proxy::getUsername).setCaption("Username").setHidden(true);
        grid.addColumn(proxy -> proxy.getPassword() != null ? Strings.repeat("*", 10) : "").setCaption("Password").setHidden(true);
        grid.addComponentColumn(client -> {
            HorizontalLayout content = new HorizontalLayout();


            return content;
        }).setCaption("Actions").setSortable(false);

        grid.setColumnReorderingAllowed(true);
        grid.setSizeFull();
        grid.getColumns().forEach(column -> column.setHidable(true));
        addComponent(grid);
    }

    private void buildAddProxy(){
        Button addProxy = new Button("Add", clickEvent -> {
            final Window window = new Window("Add Proxy");
            window.setSizeUndefined();
            AddProxyForm addProxyForm = new AddProxyForm(window);
            addProxyForm.setMargin(true);
            window.setContent(addProxyForm);
            window.addCloseListener(closeEvent -> {
                proxies.clear();
                proxies.addAll(ProxyService.getInstance().getByOwner(acuityAccount.getID()));
                grid.getDataProvider().refreshAll();
            });
            getUI().addWindow(window);
        });
        addProxy.setIcon(VaadinIcons.PLUS_CIRCLE);
        addComponent(addProxy);
    }
}
