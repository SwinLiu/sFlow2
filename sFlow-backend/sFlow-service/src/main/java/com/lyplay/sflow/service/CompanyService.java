package com.lyplay.sflow.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.lyplay.sflow.orm.components.Pagination;
import com.lyplay.sflow.service.dto.CompanyDto;
import com.lyplay.sflow.service.dto.ValueLabelBean;

public interface CompanyService {

	boolean createCompany(CompanyDto companyInfo);
	
	List<CompanyDto> getCompanyList();
	
	Pagination<CompanyDto> getCompanyListByPage(Pageable pageable);

	List<ValueLabelBean> getCompanyDropdownList(String uid);
	
	boolean deleteCompany(String compId);
	
}
