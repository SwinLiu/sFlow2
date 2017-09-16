package com.lyplay.sflow.model;

import java.io.Serializable;

public class UserSession implements Serializable{
	
	private static final long serialVersionUID = -4507115850570109126L;
	
	private String userName;
	private String email;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
