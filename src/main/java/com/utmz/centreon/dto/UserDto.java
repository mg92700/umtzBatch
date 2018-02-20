package com.utmz.centreon.dto;

import javax.persistence.Column;

public class UserDto {
	
	private Long id;
	private String login;
	private String password;
	private String ipCentreon;
	private String loginCentreon;
	private String passwordCentreon;
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
	public UserDto(Long id, String login, String password, String ipCentreon, String loginCentreon,
			String passwordCentreon) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.ipCentreon = ipCentreon;
		this.loginCentreon = loginCentreon;
		this.passwordCentreon = passwordCentreon;
	}
	@Override
	public String toString() {
		return "UserDto [id=" + id + ", login=" + login + ", password=" + password + ", ipCentreon=" + ipCentreon
				+ ", loginCentreon=" + loginCentreon + ", passwordCentreon=" + passwordCentreon + "]";
	}

	
}
