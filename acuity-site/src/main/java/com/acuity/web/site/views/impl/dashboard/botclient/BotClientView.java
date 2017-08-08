package com.acuity.web.site.views.impl.dashboard.botclient;

import com.acuity.db.arango.monitor.events.ArangoEvent;
import com.acuity.db.arango.monitor.events.wrapped.impl.BotClientEvent;
import com.acuity.db.arango.monitor.events.wrapped.impl.RSAccountAssignedToEvent;
import com.acuity.db.domain.edge.impl.AssignedTo;
import com.acuity.db.domain.vertex.impl.AcuityAccount;
import com.acuity.db.domain.vertex.impl.MessagePackage;
import com.acuity.db.domain.vertex.impl.RSAccount;
import com.acuity.db.domain.vertex.impl.bot_clients.BotClient;
import com.acuity.db.services.impl.BotClientService;
import com.acuity.db.services.impl.MessagePackageService;
import com.acuity.db.services.impl.RSAccountAssignmentService;
import com.acuity.db.services.impl.RSAccountService;
import com.acuity.web.site.events.Events;
import com.google.common.eventbus.Subscribe;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;

import java.util.List;

/**
 * Created by Zach on 8/4/2017.
 */
public class BotClientView extends VerticalLayout implements View {

    private AcuityAccount acuityAccount = VaadinSession.getCurrent().getAttribute(AcuityAccount.class);
    private BotClient botClient;

    private ComboBox<RSAccount> assignedAccount = new ComboBox<>();

    private void build(){
        Events.getDBEventBus().register(this);

        addInformationPanel();

        Button killBot = new Button("Kill Bot", clickEvent -> {
            MessagePackageService.getInstance().insert(new MessagePackage(MessagePackage.Type.DIRECT)
                    .putHeader("destinationKey", botClient.getKey())
                    .putBody("command", "kill-bot"));
        });
        killBot.setIcon(VaadinIcons.CLOSE_CIRCLE_O);
        addComponent(killBot);
    }

    private void addInformationPanel(){
        Panel panel = new Panel("<strong>Information</strong>");
        panel.setResponsive(true);
        panel.setCaptionAsHtml(true);


        GridLayout content = new GridLayout(2, 3);
        content.setResponsive(true);
        content.setSpacing(true);

        content.setStyleName("marginTop");

        content.addComponent(new Label("Client Key:"));
        content.addComponent(new Label(botClient.getKey()));

        content.addComponent(new Label("Connected:"));
        content.addComponent(new Label(botClient.getConnectionTime().toString()));

        content.addComponent(new Label("Account:"));

        content.addComponent(createAccountComboBox());

        panel.setContent(content);
        addComponent(panel);
    }

    private ComboBox createAccountComboBox(){
        assignedAccount.setHeight(25, Unit.PIXELS);
        assignedAccount.setWidth(100, Unit.PERCENTAGE);
        List<RSAccount> byOwner = RSAccountService.getInstance().getByOwner(acuityAccount.getID());
        assignedAccount.setDataProvider(new ListDataProvider<RSAccount>(byOwner));
        assignedAccount.setItemCaptionGenerator(RSAccount::getEmail);
        RSAccountAssignmentService.getInstance().getByToID(botClient.getID()).stream().findFirst().ifPresent(assignedTo -> {
            RSAccountService.getInstance().getByID(assignedTo.getFrom()).ifPresent(account -> {
                assignedAccount.setSelectedItem(account);
            });
        });
        assignedAccount.addSelectionListener(singleSelectionEvent -> {
            if (singleSelectionEvent.isUserOriginated()){
                RSAccount selectedAccount = singleSelectionEvent.getFirstSelectedItem().orElse(null);
                if (selectedAccount != null){
                    RSAccountAssignmentService.getInstance().insert(new AssignedTo(acuityAccount.getID(), selectedAccount.getID(), botClient.getID()));
                }
                else {
                    RSAccountAssignmentService.getInstance().removeByToID(botClient.getID());
                }
            }
        });

        return assignedAccount;
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        BotClientService.getInstance().getByKey(event.getParameters()).ifPresent(result -> {
            if (result.getOwnerID().equals(acuityAccount.getID())){
                botClient = result;
                Events.getDBEventBus().register(this);
            }
        });
        if (botClient == null) getUI().getNavigator().navigateTo("ERROR VIEW");
        else build();
    }

    @Override
    public void detach() {
        Events.getDBEventBus().unregister(this);
        super.detach();
    }


    @Subscribe
    public void onAssignmentUpdate(RSAccountAssignedToEvent event){
        String clientID = BotClientService.getInstance().getCollectionName() + "/" + event.getEdge().getKey();

        if (clientID.equals(botClient.getID())){
            if (event.getType() == ArangoEvent.DELETE){
                getUI().access(() -> assignedAccount.setSelectedItem(null));
            }
            else if (event.getEdge().getOwnerID().equals(acuityAccount.getID())){
                RSAccountService.getInstance().getByID(event.getEdge().getFrom()).ifPresent(account -> {
                    getUI().access(() -> assignedAccount.setSelectedItem(account));
                });
            }
        }
    }

    @Subscribe
    public void onBotClientEvent(BotClientEvent event) {
        if (event.getBotClient().getKey().equals(botClient.getKey())){
            if (event.getType() == ArangoEvent.DELETE) {
                getUI().access(() -> getUI().getNavigator().navigateTo(com.acuity.web.site.views.View.CLIENTS.getName()));
            }
            else if (event.getBotClient().getID().equals(botClient.getID())) {

            }
        }
    }
}
