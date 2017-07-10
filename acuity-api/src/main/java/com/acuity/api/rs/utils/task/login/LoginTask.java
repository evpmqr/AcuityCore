package com.acuity.api.rs.utils.task.login;

import com.acuity.api.AcuityInstance;
import com.acuity.api.rs.utils.Game;
import com.acuity.api.rs.utils.Login;
import com.acuity.api.rs.utils.Time;
import com.acuity.api.rs.utils.Timer;
import com.acuity.api.rs.utils.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by Eclipseop.
 * Date: 7/8/2017.
 */
public class LoginTask extends Task {

	private static final Logger logger = LoggerFactory.getLogger(LoginTask.class);

	private static final Timer tooManyLogins = new Timer();

	@Override
	public boolean validate() {
		if (tooManyLogins.isRunning()) {
			return false;
		}

		return Game.getGameState() == Game.LOGIN_SCREEN;
	}

	@Override
	public int loop() {
		if (AcuityInstance.getClient().isWorldSelectOpen()) {
			AcuityInstance.getAppletManager().getMouseMiddleMan()
					.dispatchClick(741, 11, true);
			Time.sleepUntil(() -> !AcuityInstance.getClient().isWorldSelectOpen(), 750);
		}

		final Account account = AcuityInstance.getRsAccount();
		if (account != null) {
			switch (Login.getLoginState()) {
				case ENTER_CREDENTIALS:

					final String loginMessage = Login.getLoginMessage();
					if (loginMessage.contains("many login attempts")) {
						logger.info("Too many login attempts! Sleeping for 2 minutes.");
						tooManyLogins.restart(120000);
					} else if (loginMessage.contains("disabled")) {
						logger.info("Account " + account.getUsername() + " is disabled. :(");
						account.setBanned(true);
					} else if (loginMessage.contains("stolen")) {
						logger.info("Account " + account.getUsername() + " is locked (not disabled).");
						account.setLocked(true);
					} else {
						Login.setLoginInfo(account);
						AcuityInstance.getAppletManager().getMouseMiddleMan()
								.dispatchClick((int) (235 + (Math.random() * (370 - 235))), (int) (305 + (Math.random() * (335 - 305))), true);
					}

					break;
				case INVALID_CREDENTIALS:
					account.setWrongLogin(true);
					Login.setLoginIndex(Login.LoginState.ENTER_CREDENTIALS.getValue());
					break;
				default:
					Login.setLoginIndex(Login.LoginState.ENTER_CREDENTIALS.getValue());
					break;
			}
		}

		return 600;
	}
}
