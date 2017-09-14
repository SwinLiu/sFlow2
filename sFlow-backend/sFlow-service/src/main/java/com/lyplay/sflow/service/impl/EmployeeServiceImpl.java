package com.lyplay.sflow.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyplay.sflow.domain.CompEmployeeGroup;
import com.lyplay.sflow.domain.Employee;
import com.lyplay.sflow.dto.EmployeeDto;
import com.lyplay.sflow.pk.CompEmployeeGroupPK;
import com.lyplay.sflow.repository.CompEmployeeGroupRepository;
import com.lyplay.sflow.repository.EmployeeRepository;
import com.lyplay.sflow.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	CompEmployeeGroupRepository compEmployeeGroupRepository;
	
	@Transactional
	@Override
	public boolean addEmployee(EmployeeDto employeeDto) {
		
		Employee employee = new Employee();
		employee.setSurName(employeeDto.getSurName());
		employee.setGivenName(employeeDto.getGivenName());
		employee.setGender(employeeDto.getGender());
		employee.setWorkEmail(employeeDto.getWorkEmail());
		employee.setStatus(employeeDto.getStatus());
		employee.setBirthday(employeeDto.getBirthday());
		employee.setChanger(employeeDto.getChanger());
		employeeRepository.save(employee);
		
		CompEmployeeGroup compEmployeeGroup = new CompEmployeeGroup();
		CompEmployeeGroupPK compEmployeeGroupPK = new CompEmployeeGroupPK(employeeDto.getCompId(), employee.getId());
		compEmployeeGroup.setId(compEmployeeGroupPK);
		compEmployeeGroup.setChanger(employeeDto.getChanger());
		compEmployeeGroupRepository.save(compEmployeeGroup);
		
		return false;
	}

}
