package com.lyplay.sflow.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lyplay.sflow.data.domain.SfComAcl;

public interface SfComAclRepository extends JpaRepository<SfComAcl, String>{
	
}
