package com.lyplay.sflow.data.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lyplay.sflow.data.BaseTest;
import com.lyplay.sflow.data.domain.Company;

public class CompanyRepositoryTest extends BaseTest{

	@Autowired
	CompanyRepository companyRepository;
	
	@Test
	public void saveCompany() {
		
		Company company = new Company();
		company.setCompanyName("Company 01");
		company.setAddress("Test Address");
		companyRepository.save(company);
		
	}
	
}
