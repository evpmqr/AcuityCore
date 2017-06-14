package com.acuity.api.rs.utils;

import com.acuity.api.AcuityInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Zachary Herridge on 6/12/2017.
 */
public class Login {

    private static final Logger logger = LoggerFactory.getLogger(Login.class);

    // TODO: 6/12/2017 Document login index values and states

    public static void setLoginInfo(String username, String password){
        AcuityInstance.getClient().setUsername(username);
        AcuityInstance.getClient().setPassword(password);
        logger.info("Set username={} and password.", username);
    }

    public static void setLoginIndex(int index){
        AcuityInstance.getClient().setLoginIndex(index);
        logger.info("Set login index to {}.", index);
    }

    public static int getLoginIndex(){
        return AcuityInstance.getClient().getLoginIndex();
    }

    public static int getLoginState(){
        return AcuityInstance.getClient().getLoginState();
    }

    public static String getLoginMessage(){
        return AcuityInstance.getClient().getLoginResponse1() + " " + AcuityInstance.getClient().getLoginResponse2();
    }
}
