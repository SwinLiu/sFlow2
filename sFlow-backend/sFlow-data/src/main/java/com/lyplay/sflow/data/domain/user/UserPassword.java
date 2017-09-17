package com.lyplay.sflow.data.domain.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity(name="sf_usr_password")
public class UserPassword implements Serializable{

	private static final long serialVersionUID = 1949404693016737504L;

	@Id
	@Column(name = "uid", nullable = false, length = 20)
	private String uid;
	
	@Column(name = "password", length = 40)
	private String password;
	
	@Column(name = "create_time")
    private Long createTime; 
	
	@Column(name = "changer", length = 20)
	private String changer;
	
	@Column(name = "update_time")
	private Long updateTime;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
