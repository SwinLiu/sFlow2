package com.lyplay.sflow.data.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

//这个注解代表CompEmployeeGroupPK这个类是用在实体里面，告诉JPA的实现产品:在实体类里面只是使用这个类定义的属性。
//简单的理解为：CompEmployeeGroupPK里的属性可以看成是CompEmployeeGroup类里的属性，好比CompEmployeeGroupPK的属性就是在CompEmployeeGroup里定义的
@Embeddable
public class CompEmployeeGroupPK implements Serializable{

	private static final long serialVersionUID = 996820852923944929L;

	@Column(name = "comp_id")
	private String compId;
	
	@Column(name = "emp_id")
	private String empId;

	public CompEmployeeGroupPK() {
		super();
	}

	public CompEmployeeGroupPK(String compId, String empId) {
		super();
		this.compId = compId;
		this.empId = empId;
	}

	public String getCompId() {
		return compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
}
