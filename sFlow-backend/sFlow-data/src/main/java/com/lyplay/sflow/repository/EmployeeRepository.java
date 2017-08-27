package com.lyplay.sflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lyplay.sflow.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Employee findById(String id);

}
