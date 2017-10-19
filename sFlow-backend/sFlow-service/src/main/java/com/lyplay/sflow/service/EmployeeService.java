package com.lyplay.sflow.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.lyplay.sflow.orm.components.Pagination;
import com.lyplay.sflow.service.dto.EmployeeDto;

public interface EmployeeService {

	boolean addEmployee(EmployeeDto employeeDto);
	
	List<EmployeeDto> getEmployeeList(String compId);
	
	Pagination<EmployeeDto> getEmployeeListByPage(String compId, Pageable pageable);
	
	boolean deleteEmployee(String empId);
	
	boolean deleteEmployee(String compId, String empployeeId);
	
}
