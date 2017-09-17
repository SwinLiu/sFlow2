package com.lyplay.sflow.service.dto;

import java.io.Serializable;

import com.lyplay.sflow.data.enums.UserAccountStatus;

public class UserViewDto implements Serializable{

	private static final long serialVersionUID = -9137884225758641006L;
	
	private String uid;
	private String userName;
	private String phoneNumber;
	private String email;
	private UserAccountStatus status;
	
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
	public UserAccountStatus getStatus() {
		return status;
	}
	public void setStatus(UserAccountStatus status) {
		this.status = status;
	}
	
}
