package com.lyplay.sflow.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lyplay.sflow.BaseTest;
import com.lyplay.sflow.domain.Company;

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
