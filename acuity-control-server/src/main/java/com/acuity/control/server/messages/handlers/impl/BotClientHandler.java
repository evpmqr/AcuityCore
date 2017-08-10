package com.acuity.control.server.messages.handlers.impl;

import com.acuity.control.server.Events;
import com.acuity.control.server.messages.handlers.MessageHandler;
import com.acuity.control.server.websockets.WSocket;
import com.acuity.control.server.websockets.WSocketEvent;
import com.acuity.db.AcuityDB;
import com.acuity.db.arango.monitor.events.ArangoEvent;
import com.acuity.db.arango.monitor.events.wrapped.impl.MessagePackageEvent;
import com.acuity.db.arango.monitor.events.wrapped.impl.bot.client.id_events.impl.RSAccountAssignedToEvent;
import com.acuity.db.domain.vertex.Vertex;
import com.acuity.db.domain.vertex.impl.AcuityAccount;
import com.acuity.db.domain.vertex.impl.Machine;
import com.acuity.db.domain.vertex.impl.MessagePackage;
import com.acuity.db.domain.vertex.impl.RSAccount;
import com.acuity.db.services.impl.BotClientConfigService;
import com.acuity.db.services.impl.BotClientService;
import com.acuity.db.services.impl.MessagePackageService;
import com.acuity.db.services.impl.RSAccountService;
import com.arangodb.ArangoCollection;
import com.arangodb.model.DocumentUpdateOptions;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * Created by Zachary Herridge on 8/3/2017.
 */
public class BotClientHandler extends MessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(BotClientHandler.class);

    private String ownerID;
    private Vertex botClient;
    private Vertex config;

    public BotClientHandler(WSocket wSocket) {
        super(wSocket);
        Events.getDBEventBus().register(this);
    }

    @Override
    public void handle(MessagePackage messagePackage) {

        if (messagePackage.getType() == MessagePackage.Type.MACHINE_INFO){
            String name = messagePackage.getBody("user.name", null);
            if (name != null){
                ArangoCollection collection = AcuityDB.getDB().db(AcuityDB.DB_NAME).collection("Machine");
                String key = ownerID + ":" + name;
                Machine machine = new Machine(ownerID, key);
                machine.getProperties().putAll(messagePackage.getBody());
                if (collection.documentExists(key)){
                    collection.updateDocument(key, machine, new DocumentUpdateOptions().mergeObjects(true));
                }
                else {
                    collection.insertDocument(machine);
                }
            }
        }
    }

    @Subscribe
    public void onMessageEvent(MessagePackageEvent event){
        if (botClient == null) return;

        if (event.getType() == ArangoEvent.CREATE_OR_UPDATE){
            String destination = event.getMessagePackage().getHeader("destinationKey", null);
            if (botClient.getKey().equals(destination)){
                getSocket().send(event.getMessagePackage());
                MessagePackageService.getInstance().delete(event.getMessagePackage());
            }
        }
    }

    @Subscribe
    public void onClose(WSocketEvent.Closed closed){
        Events.getDBEventBus().unregister(this);
        BotClientService.getInstance().removeClient(botClient.getKey());
    }

    @Subscribe
    public void assignmentChange(RSAccountAssignedToEvent event){
        if (botClient == null) return;

        String clientID = BotClientService.getInstance().getCollectionName() + "/" + event.getEdge().getKey();
        if (clientID.equals(botClient.getID())){
            if (event.getType() == ArangoEvent.DELETE){
                getSocket().send(new MessagePackage(MessagePackage.Type.ACCOUNT_ASSIGNMENT_CHANGE).putBody("account", null));
            }
            else {
                RSAccount rsAccount = RSAccountService.getInstance().getByID(event.getEdge().getFrom()).orElse(null);
                getSocket().send(new MessagePackage(MessagePackage.Type.ACCOUNT_ASSIGNMENT_CHANGE).putBody("account", rsAccount));
            }
        }
    }

    @Subscribe
    public void loginComplete(WSocketEvent.LoginComplete loginComplete){
        logger.debug("Login complete");
        AcuityAccount acuityAccount = getSocket().getSession().getAttribute(AcuityAccount.class);
        if (acuityAccount != null){
            ownerID = acuityAccount.getID();
            BotClientService.getInstance().registerClient(UUID.randomUUID().toString(), acuityAccount.getKey()).ifPresent(botClient -> {
                this.botClient = botClient;
                BotClientConfigService.getInstance().registerConfig(acuityAccount.getID(), botClient.getKey()).ifPresent(botClientConfig -> {
                    this.config = botClientConfig;
                });
            });
        }
    }
}
