package com.lyplay.sflow.data.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

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
	
	
	@Test
	public void testPage() {
		
		Sort sort = new Sort(Direction.DESC, "compId");
	    Pageable pageable = new PageRequest(2, 20, sort);
	    Page<Company> page = companyRepository.findAll(pageable);
	    System.out.println(page);
	    
	}
	
}
