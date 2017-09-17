package com.lyplay.sflow.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lyplay.sflow.data.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Employee findById(Long id);

}
