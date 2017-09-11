package com.lyplay.sflow.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.commons.lang.builder.ToStringBuilder;

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
	
	@Column(name = "create_time")
    private Long createTime; 
	
	@Column(name = "changer", length = 20)
	private String changer;
	
	@Column(name = "update_time")
	private Long updateTime;

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

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getChanger() {
		return changer;
	}

	public void setChanger(String changer) {
		this.changer = changer;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}
	
	@PreUpdate
    public void preUpdate() {
    	setUpdateTime((new Date()).getTime());
    }
    
    @PrePersist
    public void prePersist() {
        Date now = new Date();
        setCreateTime(now.getTime());
        setUpdateTime(now.getTime());
    }
    
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
