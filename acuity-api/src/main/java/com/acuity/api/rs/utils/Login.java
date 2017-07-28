package com.acuity.api.rs.utils;

import com.acuity.api.AcuityInstance;
import com.acuity.api.rs.utils.task.login.Account;
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

    public static void setLoginInfo(final Account account) {
        setLoginInfo(account.getUsername(), account.getPassword());
    }

    public static void setLoginState(final State state) {
        AcuityInstance.getClient().setLoginIndex(state.getValue());
        logger.info("Set login index to {}.", state);
    }

    public static State getLoginState(){
        return State.fromValue(AcuityInstance.getClient().getLoginState());
    }

    public static String getLoginMessage(){
        return AcuityInstance.getClient().getLoginResponse1() + " " + AcuityInstance.getClient().getLoginResponse2();
    }

    public enum State {
        INITIAL(0),
        LEGACY(1), //http://i.imgur.com/y6Kko5v.png
        ENTER_CREDENTIALS(2),
        INVALID_CREDENTIALS(3),
        AUTHENTICATOR(4),
        FORGOTTEN_PASSWORD(5),
        EMPTY(6);

        private final int value;

        State(final int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        private static State fromValue(final int value) {
            for (State state : values()) {
                if (state.value == value) {
                    return state;
                }
            }
            throw new IllegalStateException("Unknown Login.State: " + value + ".");
        }

        public boolean isCurrent(){
            return getLoginState() == this;
        }
    }
}
