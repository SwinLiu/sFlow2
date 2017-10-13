package com.lyplay.sflow.service;

import java.util.List;

import com.lyplay.sflow.service.dto.EmployeeDto;

public interface EmployeeService {

	boolean addEmployee(EmployeeDto employeeDto);
	
	List<EmployeeDto> getEmployeeList(String compId);
	
	boolean deleteEmployee(String empId);
	
	boolean deleteEmployee(String compId, String empployeeId);
	
}
