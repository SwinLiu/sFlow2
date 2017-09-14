package com.lyplay.sflow.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.lyplay.sflow.enums.EmployeeStatus;
import com.lyplay.sflow.enums.Gender;

@Entity(name="sf_emp_employee")
//Oracle中序列方式生成主键
//@SequenceGenerator(name = "SEQ", sequenceName = "SEQ_SYS_FUNC_MENU", initialValue = 0, allocationSize = 1)   
public class Employee implements Serializable{

	private static final long serialVersionUID = 2337490945537439497L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_id", nullable = false, length = 20)
	private Long id;
	
	@Column(name = "sur_name", nullable = true, length = 100)
	private String surName;
	
	@Column(name = "given_name", nullable = true, length = 100)
	private String givenName;
	
	@Transient
	private String fullName;
	
	// 性别枚举类型，显示索引值
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "gender", nullable = true, length = 1)
	private Gender gender;
	
	//日期类型，格式：yyyy-MM-dd
	@Temporal(TemporalType.DATE)
    @Column(name = "birthday")
	private Date birthday;
	
	@Column(name = "work_email", nullable = true, length = 100)
	private String workEmail;
	
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = true, length = 10)
	private EmployeeStatus status;
	
	@Column(name = "create_time")
    private Long createTime; 
	
	@Column(name = "changer", length = 20)
	private String changer;
	
	@Column(name = "update_time")
	private Long updateTime;

	@Column(name = "test_flag")
	private Boolean testFlag;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getFullName() {
		return this.getSurName() + " " + this.getGivenName();
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getWorkEmail() {
		return workEmail;
	}

	public void setWorkEmail(String workEmail) {
		this.workEmail = workEmail;
	}

	public EmployeeStatus getStatus() {
		return status;
	}

	public void setStatus(EmployeeStatus status) {
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

	public Boolean getTestFlag() {
		return testFlag;
	}

	public void setTestFlag(Boolean testFlag) {
		this.testFlag = testFlag;
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
