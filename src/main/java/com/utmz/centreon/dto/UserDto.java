package com.utmz.centreon.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.jtransfo.DomainClass;
import org.jtransfo.MappedBy;

import lombok.Data;

@Data
@DomainClass("com.utmz.centreon.model.User")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserDto {
	@MappedBy
	private Long id;
	@MappedBy
	private String login;
	@MappedBy
	private String password;
	@MappedBy
	private String ipCentreon;
	@MappedBy
	private String loginCentreon;
	@MappedBy
	private String passwordCentreon;
	@MappedBy
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

	public UserDto(Long id, String login, String password, String ipCentreon, String loginCentreon,
			String passwordCentreon, String tokenPhoneId) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.ipCentreon = ipCentreon;
		this.loginCentreon = loginCentreon;
		this.passwordCentreon = passwordCentreon;
		this.tokenPhoneId = tokenPhoneId;
	}
	@Override
	public String toString() {
		return "UserDto [id=" + id + ", login=" + login + ", password=" + password + ", ipCentreon=" + ipCentreon
				+ ", loginCentreon=" + loginCentreon + ", passwordCentreon=" + passwordCentreon + ", tokenPhoneId="
				+ tokenPhoneId + "]";
	}


	
}
