package com.lyplay.sflow.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="sf_usr_account")
public class UserAccount implements Serializable{

	private static final long serialVersionUID = -7282784267212221103L;

	@Id
	@Column(name = "uid", nullable = false, length = 20)
	private String id;
	
	@Column(name = "user_name", nullable = false, length = 40)
	private String userName;
	
	@Column(name = "email", length = 100)
	private String email;
	
	@Column(name = "phone_number", length = 20)
	private String phoneNumber;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
