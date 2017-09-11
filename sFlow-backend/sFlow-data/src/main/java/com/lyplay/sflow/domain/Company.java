package com.lyplay.sflow.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity(name="sf_com_company")
public class Company implements Serializable{

	private static final long serialVersionUID = -4759861195453752222L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comp_id", nullable = false, length = 20)
	private Long id;
	
	@Column(name = "comp_name", nullable = true, length = 100)
	private String companyName;
	
	@Column(name = "address", nullable = true, length = 200)
	private String address;
	
	@Column(name = "create_time")
    private Long createTime; 
	
	@Column(name = "changer", length = 20)
	private String changer;
	
	@Column(name = "update_time")
	private Long updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
