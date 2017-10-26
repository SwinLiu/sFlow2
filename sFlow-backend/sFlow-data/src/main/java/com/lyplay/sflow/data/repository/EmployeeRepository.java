package com.lyplay.sflow.data.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.lyplay.sflow.data.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

	Employee findByEmpId(String empId);
	
	@Query(nativeQuery = true, value="select t1.emp_id,t1.employee_id,t1.sur_name,t1.given_name, " +
			" t1.gender,t1.birthday,t1.work_email,t1.`status`,t1.test_flag, " +
			"  t1.create_time,t1.changer,t1.update_time " +
			" from sf_com_emp t1 INNER JOIN sf_com_emp_grp t2 on t1.emp_id = t2.emp_id " +
			" where t2.comp_id = ?1 ")
	List<Employee> findByCompId(String compId);
	
	@Query(nativeQuery = true, value="select t1.emp_id,t1.employee_id,t1.sur_name,t1.given_name, " +
			" t1.gender,t1.birthday,t1.work_email,t1.`status`,t1.test_flag, " +
			"  t1.create_time,t1.changer,t1.update_time " +
			" from sf_com_emp t1 INNER JOIN sf_com_emp_grp t2 on t1.emp_id = t2.emp_id " +
			" where t2.comp_id = ?1 order by ?#{#pageable} ",
			countQuery = "select count(*) from sf_com_emp t1 INNER JOIN sf_com_emp_grp t2 on t1.emp_id = t2.emp_id " +
			" where t2.comp_id = ?1 ")
	Page<Employee> findPageByCompId(String compId, Pageable pageable);
	
	@Query(nativeQuery = true, value="select t1.emp_id from sf_com_emp t1 INNER JOIN sf_com_emp_grp t2 on t1.emp_id = t2.emp_id " +
			" where t2.comp_id = ?1 and t1.employee_id = ?2 ")
	String getEmpIdBy(String compId, String employeeId);
	
	@Query(nativeQuery = true, value="delete from sf_com_emp where emp_id in ( "
			+ " select t2.emp_id from sf_com_emp_grp t2 where t2.comp_id = ?1 ) ")
	@Modifying
	void deleteByCompId(String compId);
	
}
