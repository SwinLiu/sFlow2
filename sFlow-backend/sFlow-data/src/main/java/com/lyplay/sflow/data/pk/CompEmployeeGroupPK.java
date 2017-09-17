package com.lyplay.sflow.data.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

//这个注解代表CompEmployeeGroupPK这个类是用在实体里面，告诉JPA的实现产品:在实体类里面只是使用这个类定义的属性。
//简单的理解为：CompEmployeeGroupPK里的属性可以看成是CompEmployeeGroup类里的属性，好比CompEmployeeGroupPK的属性就是在CompEmployeeGroup里定义的
@Embeddable
public class CompEmployeeGroupPK implements Serializable{

	private static final long serialVersionUID = 996820852923944929L;

	@Column(name = "comp_id", nullable = false, length = 20)
	private Long companyId;
	
	@Column(name = "emp_id", nullable = false, length = 20)
	private Long employeeId;

	public CompEmployeeGroupPK() {
		super();
	}

	public CompEmployeeGroupPK(Long companyId, Long employeeId) {
		super();
		this.companyId = companyId;
		this.employeeId = employeeId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((companyId == null) ? 0 : companyId.hashCode());
		result = prime * result
				+ ((employeeId == null) ? 0 : employeeId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompEmployeeGroupPK other = (CompEmployeeGroupPK) obj;
		if (companyId == null) {
			if (other.companyId != null)
				return false;
		} else if (!companyId.equals(other.companyId))
			return false;
		if (employeeId == null) {
			if (other.employeeId != null)
				return false;
		} else if (!employeeId.equals(other.employeeId))
			return false;
		return true;
	}
	
}
