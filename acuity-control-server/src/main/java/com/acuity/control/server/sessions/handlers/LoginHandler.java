package com.acuity.control.server.sessions.handlers;

import com.acuity.control.server.sessions.SocketSession;
import com.acuity.db.domain.vertex.impl.AcuityAccount;
import com.acuity.db.domain.vertex.impl.MessagePackage;
import com.acuity.db.services.AcuityAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Zachary Herridge on 8/3/2017.
 */
public class LoginHandler extends MessageHandler{

    private static final Logger logger = LoggerFactory.getLogger(LoginHandler.class);

    private static final MessagePackage BAD_LOGIN = new MessagePackage(MessagePackage.Type.BAD_LOGIN);

    public LoginHandler(SocketSession socketSession) {
        super(socketSession);
    }


    @Override
    public void handle(MessagePackage messagePackage) {
        Double messageType = messagePackage.getHeader("messageType", (double) MessagePackage.Type.UNKNOWN);
        logger.info(messagePackage.toString());
        if (messageType == MessagePackage.Type.LOGIN){

            String username = messagePackage.getBody("username", null);
            String password = messagePackage.getBody("password", null);
            Double sessionType = messagePackage.getBody("sessionType", null);

            if (username != null && password != null && sessionType != null){
                AcuityAccount acuityAccount = AcuityAccountService.getInstance()
                        .checkLogin(username, password)
                        .orElse(null);

                if (acuityAccount != null){
                    if (sessionType == 1){
                        getSocket().setCurrentHandler(new BotSessionHandler(getSocket()));
                    }
                    else {
                        getSocket().send(BAD_LOGIN);
                        return;
                    }
                    getSocket().setAcuityAccount(acuityAccount);
                    getSocket().send(new MessagePackage(MessagePackage.Type.GOOD_LOGIN)
                            .putBody("acuityAccount", acuityAccount));
                }
                else {
                    getSocket().send(BAD_LOGIN);
                }
            }
        }
    }
}
