package com.lyplay.sflow.data.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lyplay.sflow.data.BaseTest;
import com.lyplay.sflow.data.domain.CompEmployeeGroup;
import com.lyplay.sflow.data.pk.CompEmployeeGroupPK;

public class CompEmployeeGroupRepositoryTest extends BaseTest{

	@Autowired
	CompEmployeeGroupRepository compEmployeeGroupRepository;
	
	@Test
	public void saveCompEmployeeGroup() {
		
		CompEmployeeGroup compEmployeeGroup = new CompEmployeeGroup();
		CompEmployeeGroupPK pk = new CompEmployeeGroupPK();
		pk.setCompanyId(1l);
		pk.setEmployeeId(1l);
		compEmployeeGroup.setId(pk);
		compEmployeeGroupRepository.save(compEmployeeGroup);
		
	}
	
}
