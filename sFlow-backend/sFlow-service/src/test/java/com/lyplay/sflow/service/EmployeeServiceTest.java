package com.lyplay.sflow.service;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lyplay.sflow.BaseTest;
import com.lyplay.sflow.data.enums.Gender;
import com.lyplay.sflow.service.dto.EmployeeDto;

public class EmployeeServiceTest extends BaseTest{

	@Autowired
	EmployeeService employeeService;
	
	@Test
	public void addEmployeeTest() {
		
		EmployeeDto employeeDto = new EmployeeDto();
		
		employeeDto.setSurName("Swin02");
		employeeDto.setGivenName("Liu02");
		employeeDto.setChanger("test");
		employeeDto.setWorkEmail("haa@123.com");
		employeeDto.setBirthday(new Date());
		employeeDto.setGender(Gender.FEMALE);
		employeeDto.setCompId(1l);
		
		employeeService.addEmployee(employeeDto);
	}
}
