package com.lyplay.sflow.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyplay.sflow.data.domain.Company;
import com.lyplay.sflow.data.repository.CompanyRepository;
import com.lyplay.sflow.service.CompanyService;
import com.lyplay.sflow.service.dto.CompanyDto;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	CompanyRepository companyRepository;
	
	@Override
	public boolean createCompany(CompanyDto companyInfo) {
		Company company = new Company();
		company.setCompanyName(companyInfo.getCompanyName());
		company.setAddress(companyInfo.getAddress());
		company.setChanger(companyInfo.getAddress());
		companyRepository.save(company);
		return true;
	}
	
	@Override
	public List<CompanyDto> getCompanyList() {
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

}
