package com.acuity.api.rs.utils;

import com.acuity.api.AcuityInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Zachary Herridge on 6/12/2017.
 */
public class Login {

    private static final Logger logger = LoggerFactory.getLogger(Login.class);

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

    public static LoginState getLoginState(){
        return LoginState.fromValue(AcuityInstance.getClient().getLoginState());
    }

    public static String getLoginMessage(){
        return AcuityInstance.getClient().getLoginResponse1() + " " + AcuityInstance.getClient().getLoginResponse2();
    }

    public enum LoginState {
        INITIAL(0),
        LEGACY(1), //http://i.imgur.com/y6Kko5v.png
        ENTER_CREDENTIALS(2),
        INVALID_CREDENTIALS(3),
        AUTHENTICATOR(4),
        FORGOTTEN_PASSWORD(5),
        EMPTY(6);

        private final int value;

        LoginState(final int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        private static LoginState fromValue(final int value) {
            for (LoginState loginState : values()) {
                if (loginState.value == value) {
                    return loginState;
                }
            }
            return null;
        }
    }
}
