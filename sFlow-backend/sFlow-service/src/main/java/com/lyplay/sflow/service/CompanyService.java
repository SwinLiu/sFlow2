package com.lyplay.sflow.service;

import java.util.List;

import com.lyplay.sflow.service.dto.CompanyDto;

public interface CompanyService {

	boolean createCompany(CompanyDto companyInfo);
	
	List<CompanyDto> getCompanyList();
	
}
