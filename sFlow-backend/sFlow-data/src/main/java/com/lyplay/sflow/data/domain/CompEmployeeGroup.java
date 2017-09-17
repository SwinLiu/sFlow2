package com.lyplay.sflow.data.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.lyplay.sflow.data.pk.CompEmployeeGroupPK;

@Entity(name="sf_com_emp_grp")
public class CompEmployeeGroup implements Serializable{

	private static final long serialVersionUID = -4759861195453752222L;

	@EmbeddedId
	private CompEmployeeGroupPK id;
	
	@Column(name = "create_time")
    private Long createTime; 
	
	@Column(name = "changer", length = 20)
	private String changer;
	
	@Column(name = "update_time")
	private Long updateTime;

	public CompEmployeeGroupPK getId() {
		return id;
	}

	public void setId(CompEmployeeGroupPK id) {
		this.id = id;
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
    	updateTime = (new Date()).getTime();
    }
    
    @PrePersist
    public void prePersist() {
        Date now = new Date();
        createTime = now.getTime();
        updateTime = now.getTime();
    }
    
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
