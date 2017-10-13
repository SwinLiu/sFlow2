package com.lyplay.sflow.service;

import java.util.List;

import com.lyplay.sflow.service.dto.CompanyDto;
import com.lyplay.sflow.service.dto.ValueLabelBean;

public interface CompanyService {

	boolean createCompany(CompanyDto companyInfo);
	
	List<CompanyDto> getCompanyList();

	List<ValueLabelBean> getCompanyDropdownList(String uid);
	
	boolean deleteCompany(String compId);
	
}
