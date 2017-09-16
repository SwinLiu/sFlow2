package com.lyplay.sflow.dto;

import java.io.Serializable;

public class UserDto implements Serializable{

	private static final long serialVersionUID = 5598995411757451341L;
	
	private String userName;
	private String phoneNumber;
	private String email;
	
	private String password;
	
	private String changer;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getChanger() {
		return changer;
	}
	public void setChanger(String changer) {
		this.changer = changer;
	}
	
}
