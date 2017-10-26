package com.lyplay.sflow.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lyplay.sflow.data.domain.SfComOrg;

public interface SfComOrgRepository extends JpaRepository<SfComOrg, String>{
	
}
