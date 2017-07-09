package com.acuity.api.rs.utils.task.login;

import com.acuity.api.AcuityInstance;
import com.acuity.api.rs.utils.Game;
import com.acuity.api.rs.utils.Login;
import com.acuity.api.rs.utils.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Eclipseop.
 * Date: 7/8/2017.
 */
public class LoginTask extends Task {

	private static final Logger logger = LoggerFactory.getLogger(LoginTask.class);

	@Override
	public boolean validate() {
		return Game.getGameState() == Game.LOGIN_SCREEN;
	}

	@Override
	public int loop() {
		final Account account = AcuityInstance.getRsAccount();
		if (account != null) {
			switch (Login.getLoginState()) {
				case INITIAL:
					Login.setLoginIndex(Login.LoginState.ENTER_CREDENTIALS.getValue());
					break;
				case ENTER_CREDENTIALS:
					Login.setLoginInfo(account);
					AcuityInstance.getAppletManager().getMouseMiddleMan()
							.dispatchClick((int) (235 + (Math.random() * (370 - 235))), (int) (305 + (Math.random() * (335 - 305))), true);
					break;
				case INVALID_CREDENTIALS:
					account.setWrongLogin(true);
					Login.setLoginIndex(Login.LoginState.ENTER_CREDENTIALS.getValue());
					break;
				case FORGOTTEN_PASSWORD:
					logger.warn("Should not have landed here");
					Login.setLoginIndex(Login.LoginState.ENTER_CREDENTIALS.getValue());
					break;
			}
		}

		return 600;
	}
}
