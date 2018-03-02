package com.utmz.centreon.modelApi.centreon;

import org.jtransfo.MappedBy;

public class userDB {
	
	
	private Long id;

	private String login;

	private String password;

	private String ipCentreon;

	private String loginCentreon;

	private String passwordCentreon;

	private String tokenPhoneId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIpCentreon() {
		return ipCentreon;
	}

	public void setIpCentreon(String ipCentreon) {
		this.ipCentreon = ipCentreon;
	}

	public String getLoginCentreon() {
		return loginCentreon;
	}

	public void setLoginCentreon(String loginCentreon) {
		this.loginCentreon = loginCentreon;
	}

	public String getPasswordCentreon() {
		return passwordCentreon;
	}

	public void setPasswordCentreon(String passwordCentreon) {
		this.passwordCentreon = passwordCentreon;
	}

	public String getTokenPhoneId() {
		return tokenPhoneId;
	}

	public void setTokenPhoneId(String tokenPhoneId) {
		this.tokenPhoneId = tokenPhoneId;
	}

	
	
}
