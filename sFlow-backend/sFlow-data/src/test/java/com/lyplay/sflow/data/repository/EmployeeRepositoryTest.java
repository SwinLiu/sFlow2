package com.lyplay.sflow.data.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lyplay.sflow.data.BaseTest;
import com.lyplay.sflow.data.domain.Employee;
import com.lyplay.sflow.data.enums.Gender;

public class EmployeeRepositoryTest extends BaseTest{

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Test
	public void employeeTest() {
		
		Employee employee = new Employee();
		employee.setEmployeeId("E0001");
		employee.setSurName("Liu");
		employee.setGivenName("Swin");
		employee.setGender(Gender.MALE);
		employee.setWorkEmail("i@lyplay.com");
		
		employeeRepository.save(employee);
		
		updateEmployee(employee);
		
		Employee empTemp = employeeRepository.findByEmpId(employee.getEmpId());
		
		System.out.println(empTemp);
		
		deleteEmployee(empTemp.getEmpId());
		
	}
	
	
	public void updateEmployee(Employee employee) {
		
		employee.setSurName("Liu01");
		employee.setGivenName("Swin01");
		employee.setGender(Gender.FEMALE);
		employee.setWorkEmail("ly@lyplay.com");
		
		employeeRepository.save(employee);
		
	}
	
	public void deleteEmployee(String id) {
		employeeRepository.delete(id);
	}
	
}
