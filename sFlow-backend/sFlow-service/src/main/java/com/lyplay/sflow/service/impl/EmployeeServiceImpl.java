package com.lyplay.sflow.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyplay.sflow.data.domain.CompEmployeeGroup;
import com.lyplay.sflow.data.domain.Employee;
import com.lyplay.sflow.data.pk.CompEmployeeGroupPK;
import com.lyplay.sflow.data.repository.CompEmployeeGroupRepository;
import com.lyplay.sflow.data.repository.EmployeeRepository;
import com.lyplay.sflow.service.EmployeeService;
import com.lyplay.sflow.service.dto.EmployeeDto;

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
		employee.setEmployeeId(employeeDto.getEmployeeId());
		employee.setSurName(employeeDto.getSurName());
		employee.setGivenName(employeeDto.getGivenName());
		employee.setGender(employeeDto.getGender());
		employee.setWorkEmail(employeeDto.getWorkEmail());
		employee.setStatus(employeeDto.getStatus());
		employee.setBirthday(employeeDto.getBirthday());
		employee.setChanger(employeeDto.getChanger());
		employeeRepository.save(employee);
		
		CompEmployeeGroup compEmployeeGroup = new CompEmployeeGroup();
		CompEmployeeGroupPK compEmployeeGroupPK = new CompEmployeeGroupPK(employeeDto.getCompId(), employee.getEmpId());
		compEmployeeGroup.setId(compEmployeeGroupPK);
		compEmployeeGroup.setChanger(employeeDto.getChanger());
		compEmployeeGroupRepository.save(compEmployeeGroup);
		
		return false;
	}

}
