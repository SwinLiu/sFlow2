package com.lyplay.sflow.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lyplay.sflow.pk.CompEmployeeGroupPK;

@Entity(name="sf_em_company_employee")
public class CompEmployeeGroup implements Serializable{

	private static final long serialVersionUID = -4759861195453752222L;

	@EmbeddedId
	private CompEmployeeGroupPK id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
    private Date createTime; 
	
	@Column(name = "changer", length = 20)
	private String changer;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time")
	private Date updateTime;

	public CompEmployeeGroupPK getId() {
		return id;
	}

	public void setId(CompEmployeeGroupPK id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getChanger() {
		return changer;
	}

	public void setChanger(String changer) {
		this.changer = changer;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
