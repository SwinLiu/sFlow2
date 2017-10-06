package com.lyplay.sflow.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lyplay.sflow.data.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

	Employee findByEmpId(String empId);
	
	@Query("fromEduTeachers t where t.name like ?1 and t.org.orgname like ?2")
	List<Employee> findByCompId(String compId);

}
