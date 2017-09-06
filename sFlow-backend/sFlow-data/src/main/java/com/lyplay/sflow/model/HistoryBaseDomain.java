package com.lyplay.sflow.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class HistoryBaseDomain extends BaseDomain{

	@Column(name = "changer", length = 20)
	private String changer;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
    private Long createTime; 
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time")
	private Long updateTime;

    @PreUpdate
    public void preUpdate() {
    	updateTime = new Date().getTime();
    }
    
    @PrePersist
    public void prePersist() {
        Date now = new Date();
        createTime = now.getTime();
        updateTime = now.getTime();
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
	
}
