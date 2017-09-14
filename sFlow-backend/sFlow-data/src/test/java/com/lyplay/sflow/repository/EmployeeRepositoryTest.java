package com.lyplay.sflow.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lyplay.sflow.BaseTest;
import com.lyplay.sflow.domain.Employee;
import com.lyplay.sflow.enums.Gender;

public class EmployeeRepositoryTest extends BaseTest{

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Test
	public void employeeTest() {
		
		Employee employee = new Employee();
		employee.setSurName("Liu");
		employee.setGivenName("Swin");
		employee.setGender(Gender.MALE);
		employee.setWorkEmail("i@lyplay.com");
		
		employeeRepository.save(employee);
		
		updateEmployee(employee);
		
		Employee empTemp = employeeRepository.findById(employee.getId());
		
		System.out.println(empTemp);
		
		//deleteEmployee(empTemp.getId());
		
	}
	
	
	public void updateEmployee(Employee employee) {
		
		employee.setSurName("Liu01");
		employee.setGivenName("Swin01");
		employee.setGender(Gender.FEMALE);
		employee.setWorkEmail("ly@lyplay.com");
		
		employeeRepository.save(employee);
		
	}
	
	public void deleteEmployee(Long id) {
		employeeRepository.delete(id);
	}
	
}
