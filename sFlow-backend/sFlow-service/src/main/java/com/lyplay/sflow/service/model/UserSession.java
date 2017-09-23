package com.lyplay.sflow.service.model;

import java.io.Serializable;

public class UserSession implements Serializable{
	
	private static final long serialVersionUID = -4507115850570109126L;
	
	private String uid;
	private String userName;
	private String email;
	
	private String jwtToken;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
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
	public String getJwtToken() {
		return jwtToken;
	}
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	
}
