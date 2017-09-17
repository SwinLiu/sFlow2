package com.lyplay.sflow.data.domain.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity(name="sf_usr_log")
public class UserLog implements Serializable{

	private static final long serialVersionUID = -1259707079967868807L;

	@Id
	@Column(name = "uid", nullable = false, length = 20)
	private String uid;
	
	@Id
	@Column(name = "login_time")
	private Long loginTime;
	
	@Column(name = "logout_time")
	private Long logoutTime;
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Long getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Long loginTime) {
		this.loginTime = loginTime;
	}

	public Long getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(Long logoutTime) {
		this.logoutTime = logoutTime;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
