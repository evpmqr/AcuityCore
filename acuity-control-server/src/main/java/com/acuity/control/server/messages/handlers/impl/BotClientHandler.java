package com.acuity.control.server.messages.handlers.impl;

import com.acuity.control.server.messages.handlers.MessageHandler;
import com.acuity.control.server.websockets.WSocket;
import com.acuity.control.server.websockets.WSocketEvent;
import com.acuity.db.domain.vertex.impl.AcuityAccount;
import com.acuity.db.domain.vertex.impl.MessagePackage;
import com.acuity.db.services.impl.BotClientService;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Zachary Herridge on 8/3/2017.
 */
public class BotClientHandler extends MessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(BotClientHandler.class);

    private String botClientKey;

    public BotClientHandler(WSocket wSocket) {
        super(wSocket);
    }

    @Override
    public void handle(MessagePackage messagePackage) {

    }

    @Subscribe
    public void onClose(WSocketEvent.Closed closed){
        BotClientService.getInstance().removeClient(botClientKey);
    }

    @Subscribe
    public void loginComplete(WSocketEvent.LoginComplete loginComplete){
        logger.debug("Login complete");
        AcuityAccount acuityAccount = getSocket().getSession().getAttribute(AcuityAccount.class);
        if (acuityAccount != null){
            BotClientService.getInstance().registerClient(acuityAccount.getKey()).ifPresent(botClient -> botClientKey = botClient.getKey());
        }
    }
}
