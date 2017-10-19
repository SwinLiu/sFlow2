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

import com.lyplay.sflow.data.domain.Company;
import com.lyplay.sflow.data.repository.CompEmployeeGroupRepository;
import com.lyplay.sflow.data.repository.CompanyRepository;
import com.lyplay.sflow.data.repository.EmployeeRepository;
import com.lyplay.sflow.orm.components.Pagination;
import com.lyplay.sflow.service.CompanyService;
import com.lyplay.sflow.service.dto.CompanyDto;
import com.lyplay.sflow.service.dto.ValueLabelBean;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	CompanyRepository companyRepository;
	@Autowired
	CompEmployeeGroupRepository compEmployeeGroupRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public boolean createCompany(CompanyDto companyInfo) {
		Company company = new Company();
		company.setCompanyName(companyInfo.getCompanyName());
		company.setAddress(companyInfo.getAddress());
		company.setChanger(companyInfo.getChanger());
		companyRepository.save(company);
		return true;
	}
	
	@Override
	public List<CompanyDto> getCompanyList() {
		// TODO add order by
		List<Company> compList = companyRepository.findAll();
		if(CollectionUtils.isNotEmpty(compList)){
			List<CompanyDto> compDtoList = new ArrayList<CompanyDto>();
			for(Company company : compList){
				CompanyDto companyDto = new CompanyDto();
				companyDto.setCompId(company.getCompId());
				companyDto.setCompanyName(company.getCompanyName());
				companyDto.setAddress(company.getAddress());
				companyDto.setChanger(company.getChanger());
				compDtoList.add(companyDto);
			}
			return compDtoList;
		} else {
			return Collections.emptyList();
		}
	}
	
	@Override
	public Pagination<CompanyDto> getCompanyListByPage(Pageable pageable) {
		// TODO add order by
		Page<Company> compPageData = companyRepository.findAll(pageable);
		if(CollectionUtils.isNotEmpty(compPageData.getContent())){
			List<CompanyDto> compDtoList = new ArrayList<CompanyDto>(compPageData.getContent().size());
			for(Company company : compPageData.getContent()){
				CompanyDto companyDto = new CompanyDto();
				companyDto.setCompId(company.getCompId());
				companyDto.setCompanyName(company.getCompanyName());
				companyDto.setAddress(company.getAddress());
				companyDto.setChanger(company.getChanger());
				compDtoList.add(companyDto);
			}
			return new Pagination<CompanyDto>(compPageData.getSize(), compPageData.getTotalElements(), compPageData.getNumber() + 1, compDtoList);
		} else {
			return new Pagination<CompanyDto>();
		}
	}
	
	@Override
	public List<ValueLabelBean> getCompanyDropdownList(String uid) {
		//TODO user mapping data & order by condition
		List<Company> compList = companyRepository.findAll();
		if(CollectionUtils.isNotEmpty(compList)){
			List<ValueLabelBean> vlBeanList = new ArrayList<ValueLabelBean>();
			for(Company company : compList){
				ValueLabelBean vlBean = new ValueLabelBean();
				vlBean.setValue(company.getCompId());
				vlBean.setLabel(company.getCompanyName());
				vlBeanList.add(vlBean);
			}
			return vlBeanList;
		} else {
			return Collections.emptyList();
		}
	}
	
	@Transactional
	@Override
	public boolean deleteCompany(String compId) {
		employeeRepository.deleteByCompId(compId);
		compEmployeeGroupRepository.deleteCompany(compId);
		companyRepository.delete(compId);
		return true;
	}

}
