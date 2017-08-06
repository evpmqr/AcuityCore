package com.acuity.control.server.messages.handlers.impl;

import com.acuity.control.server.Events;
import com.acuity.control.server.messages.handlers.MessageHandler;
import com.acuity.control.server.websockets.WSocket;
import com.acuity.control.server.websockets.WSocketEvent;
import com.acuity.db.arango.monitor.events.ArangoEvent;
import com.acuity.db.arango.monitor.events.wrapped.impl.MessagePackageEvent;
import com.acuity.db.domain.vertex.impl.AcuityAccount;
import com.acuity.db.domain.vertex.impl.MessagePackage;
import com.acuity.db.domain.vertex.impl.botclient.BotClientConfig;
import com.acuity.db.services.impl.BotClientConfigService;
import com.acuity.db.services.impl.BotClientService;
import com.acuity.db.services.impl.MessagePackageService;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Zachary Herridge on 8/3/2017.
 */
public class BotClientHandler extends MessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(BotClientHandler.class);

    private String botClientKey;
    private String configKey;

    public BotClientHandler(WSocket wSocket) {
        super(wSocket);
        Events.getDBEventBus().register(this);
    }

    @Override
    public void handle(MessagePackage messagePackage) {

    }

    @Subscribe
    public void onMessageEvent(MessagePackageEvent event){
        if (event.getType() == ArangoEvent.CREATE_OR_UPDATE){
            String destination = event.getMessagePackage().getHeader("destinationKey", null);
            if (botClientKey.equals(destination)){
                getSocket().send(event.getMessagePackage());
                MessagePackageService.getInstance().delete(event.getMessagePackage());
            }
        }
    }

    @Subscribe
    public void onClose(WSocketEvent.Closed closed){
        Events.getDBEventBus().unregister(this);
        BotClientService.getInstance().removeClient(botClientKey);
        BotClientConfigService.getInstance().removeConfig(configKey);
    }

    @Subscribe
    public void loginComplete(WSocketEvent.LoginComplete loginComplete){
        logger.debug("Login complete");
        AcuityAccount acuityAccount = getSocket().getSession().getAttribute(AcuityAccount.class);
        if (acuityAccount != null){
            BotClientService.getInstance().registerClient(acuityAccount.getKey()).ifPresent(botClient -> {
                botClientKey = botClient.getKey();
                BotClientConfigService.getInstance().registerConfig(acuityAccount.getKey(), botClientKey).ifPresent(botClientConfig -> {
                    configKey = botClientConfig.getKey();

                });
            });
        }
    }
}
