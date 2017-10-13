package com.lyplay.sflow.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.lyplay.sflow.data.domain.CompEmployeeGroup;
import com.lyplay.sflow.data.pk.CompEmployeeGroupPK;

public interface CompEmployeeGroupRepository extends JpaRepository<CompEmployeeGroup, CompEmployeeGroupPK> {

	CompEmployeeGroup findById(CompEmployeeGroupPK id);

	@Query(nativeQuery = true, value="delete from sf_com_emp_grp where emp_id = ?1 ")
	@Modifying
	void deleteEmployee(String empId);
	
	@Query(nativeQuery = true, value="delete from sf_com_emp_grp where comp_id = ?1 ")
	@Modifying
	void deleteCompany(String compId);
	
}
