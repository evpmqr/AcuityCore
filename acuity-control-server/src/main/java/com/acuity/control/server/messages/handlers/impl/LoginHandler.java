package com.acuity.control.server.messages.handlers.impl;

import com.acuity.control.server.messages.handlers.MessageHandler;
import com.acuity.control.server.sessions.Session;
import com.acuity.control.server.sessions.Sessions;
import com.acuity.control.server.websockets.WSocket;
import com.acuity.control.server.websockets.WSocketEvent;
import com.acuity.db.domain.vertex.impl.AcuityAccount;
import com.acuity.db.domain.vertex.impl.MessagePackage;
import com.acuity.db.services.impl.AcuityAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Zachary Herridge on 8/3/2017.
 */
public class LoginHandler extends MessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(LoginHandler.class);

    private static final MessagePackage BAD_LOGIN = new MessagePackage(MessagePackage.Type.BAD_LOGIN);

    public LoginHandler(WSocket wSocket) {
        super(wSocket);
    }

    @Override
    public void handle(MessagePackage messagePackage) {
        Double messageType = messagePackage.getType();
        if (messageType == MessagePackage.Type.LOGIN){

            String username = messagePackage.getBody("username", null);
            String password = messagePackage.getBody("password", null);
            Double sessionType = messagePackage.getBody("sessionType", null);

            if (username != null && password != null && sessionType != null){
                logger.debug("Attempting login for '{}'.", username);
                AcuityAccount acuityAccount = AcuityAccountService.getInstance()
                        .checkLoginByEmail(username, password)
                        .orElse(null);

                if (acuityAccount != null){
                    logger.debug("Found account for '{}'.", username);
                    if (sessionType == 1){
                        logger.debug("Session type found for {}.", sessionType);
                        getSocket().getEventBus().register(new BotClientHandler(getSocket()));
                    }
                    else {
                        logger.debug("Failed to find session type found for {}.", sessionType);
                        getSocket().send(BAD_LOGIN);
                        return;
                    }

                    Session session = getSocket().getSession();
                    if (session == null){
                        session = Sessions.createSession();
                        getSocket().setSession(session);
                    }
                    session.setAttribute(AcuityAccount.class, acuityAccount);
                    destroy();
                    getSocket().send(new MessagePackage(MessagePackage.Type.GOOD_LOGIN).putBody("acuityAccount", acuityAccount));
                    getSocket().getEventBus().post(new WSocketEvent.LoginComplete(messagePackage));
                }
                else {
                    logger.debug("Bad login for '{}'.", username);
                    getSocket().send(BAD_LOGIN);
                }
            }
        }
    }
}
