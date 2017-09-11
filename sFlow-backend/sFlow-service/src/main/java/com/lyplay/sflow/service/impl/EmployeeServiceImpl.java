package com.lyplay.sflow.service.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lyplay.sflow.dto.EmployeeDto;
import com.lyplay.sflow.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Transactional
	@Override
	public boolean addEmployee(EmployeeDto employee) {
		// TODO Auto-generated method stub
		return false;
	}

}
