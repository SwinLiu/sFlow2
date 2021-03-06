package com.lyplay.sflow.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lyplay.sflow.data.domain.CompEmployeeGroup;
import com.lyplay.sflow.data.domain.Employee;
import com.lyplay.sflow.data.enums.EmployeeStatus;
import com.lyplay.sflow.data.pk.CompEmployeeGroupPK;
import com.lyplay.sflow.data.repository.CompEmployeeGroupRepository;
import com.lyplay.sflow.data.repository.EmployeeRepository;
import com.lyplay.sflow.orm.components.Pagination;
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
		employee.setStatus(EmployeeStatus.ACTIVE);
		employeeRepository.save(employee);
		
		CompEmployeeGroup compEmployeeGroup = new CompEmployeeGroup();
		CompEmployeeGroupPK compEmployeeGroupPK = new CompEmployeeGroupPK(employeeDto.getCompId(), employee.getEmpId());
		compEmployeeGroup.setId(compEmployeeGroupPK);
		compEmployeeGroup.setChanger(employeeDto.getChanger());
		compEmployeeGroupRepository.save(compEmployeeGroup);
		
		return false;
	}
	
	@Override
	public List<EmployeeDto> getEmployeeList(String compId) {
		List<Employee> employeeList = employeeRepository.findByCompId(compId);
		if(CollectionUtils.isNotEmpty(employeeList)){
			List<EmployeeDto> employeeDtoList = new ArrayList<EmployeeDto>();
			for(Employee employee : employeeList){
				EmployeeDto employeeDto = new EmployeeDto();
				employeeDto.setCompId(compId);
				employeeDto.setEmpId(employee.getEmpId());
				employeeDto.setEmployeeId(employee.getEmployeeId());
				employeeDto.setSurName(employee.getSurName());
				employeeDto.setGivenName(employee.getGivenName());
				employeeDto.setGender(employee.getGender());
				employeeDto.setBirthday(employee.getBirthday());
				employeeDto.setWorkEmail(employee.getWorkEmail());
				employeeDto.setStatus(employee.getStatus());
				employeeDtoList.add(employeeDto);
			}
			return employeeDtoList;
		} else {
			return Collections.emptyList();
		}
	}
	
	@Override
	public Pagination<EmployeeDto> getEmployeeListByPage(String compId, Pageable pageable) {
		Page<Employee> employeePageData = employeeRepository.findPageByCompId(compId, pageable);
		if(CollectionUtils.isNotEmpty(employeePageData.getContent())){
			List<EmployeeDto> employeeDtoList = new ArrayList<EmployeeDto>();
			for(Employee employee : employeePageData.getContent()){
				EmployeeDto employeeDto = new EmployeeDto();
				employeeDto.setCompId(compId);
				employeeDto.setEmpId(employee.getEmpId());
				employeeDto.setEmployeeId(employee.getEmployeeId());
				employeeDto.setSurName(employee.getSurName());
				employeeDto.setGivenName(employee.getGivenName());
				employeeDto.setGender(employee.getGender());
				employeeDto.setBirthday(employee.getBirthday());
				employeeDto.setWorkEmail(employee.getWorkEmail());
				employeeDto.setStatus(employee.getStatus());
				employeeDtoList.add(employeeDto);
			}
			return new Pagination<EmployeeDto>(employeePageData.getSize(), employeePageData.getTotalElements(), employeePageData.getNumber() + 1, employeeDtoList);
		} else {
			return new Pagination<EmployeeDto>();
		}
	}
	
	@Transactional
	@Override
	public boolean deleteEmployee(String empId) {
		// TODO Auto-generated method stub
		compEmployeeGroupRepository.deleteEmployee(empId);
		employeeRepository.delete(empId);
		return true;
	}
	
	@Override
	public boolean deleteEmployee(String compId, String employeeId) {
		String empId = employeeRepository.getEmpIdBy(compId, employeeId);
		return deleteEmployee(empId);
	}

}
