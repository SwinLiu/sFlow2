package com.lyplay.sflow.data.domain;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

import com.lyplay.sflow.data.enums.EmployeeStatus;
import com.lyplay.sflow.data.enums.Gender;

@Entity(name="sf_com_emp")
public class Employee implements Serializable{

	private static final long serialVersionUID = 2337490945537439497L;

	@Id
	@GeneratedValue(generator="sFlowEmpIdKeyGenerator")
	@GenericGenerator(name = "sFlowEmpIdKeyGenerator", strategy = "com.lyplay.sflow.data.util.EmpIdKeyGenerator")
	@Column(name = "emp_id")
	private String empId;
	
	@Column(name = "employee_id")
	private String employeeId;
	
	@Column(name = "sur_name")
	private String surName;
	
	@Column(name = "given_name")
	private String givenName;
	
	@Transient
	private String fullName;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "gender")
	private Gender gender;
	
	//日期类型，格式：yyyy-MM-dd
	@Temporal(TemporalType.DATE)
    @Column(name = "birthday")
	private Date birthday;
	
	@Column(name = "work_email")
	private String workEmail;
	
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private EmployeeStatus status;
	
	@Column(name = "create_time")
    private Long createTime; 
	
	@Column(name = "changer")
	private String changer;
	
	@Column(name = "update_time")
	private Long updateTime;

	@Column(name = "test_flag")
	private Boolean testFlag;
	
	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
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
