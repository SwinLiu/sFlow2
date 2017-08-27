package com.lyplay.sflow.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lyplay.sflow.BaseTest;
import com.lyplay.sflow.domain.Employee;

public class EmployeeRepositoryTest extends BaseTest{

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Test
	public void saveRmployee() {
		
		Employee employee = new Employee();
		employee.setSurName("Liu");
		employee.setGiven_name("Swin");
		employee.setGender("1");
		employee.setWork_email("i@lyplay.com");
		
		employeeRepository.save(employee);
		
	}
	
}
