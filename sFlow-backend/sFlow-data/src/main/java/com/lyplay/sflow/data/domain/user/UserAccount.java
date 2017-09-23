package com.lyplay.sflow.data.domain.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

import com.lyplay.sflow.data.enums.UserAccountStatus;

@Entity(name="sf_usr_account")
public class UserAccount implements Serializable{

	private static final long serialVersionUID = -7282784267212221103L;

	@Id
	@GeneratedValue(generator="sFlowUidKeyGenerator")
	@GenericGenerator(name = "sFlowUidKeyGenerator", strategy = "com.lyplay.sflow.data.util.UserAccountKeyGenerator")
	@Column(name = "uid")
	private String uid;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status")
	private UserAccountStatus status;
	
	@Column(name = "create_time")
    private Long createTime; 
	
	@Column(name = "changer")
	private String changer;
	
	@Column(name = "update_time")
	private Long updateTime;


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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public UserAccountStatus getStatus() {
		return status;
	}

	public void setStatus(UserAccountStatus status) {
		this.status = status;
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
