package com.acuity.api.rs.utils.task.login;

/**
 * Created by Eclipseop.
 * Date: 7/8/2017.
 */
public class Account {

	private final String username;
	private final String password;
	private boolean wrongLogin;

	public Account(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public boolean isWrongLogin() {
		return wrongLogin;
	}

	public void setWrongLogin(boolean wrongLogin) {
		this.wrongLogin = wrongLogin;
	}
}
