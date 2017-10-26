package com.lyplay.sflow.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lyplay.sflow.data.domain.SfComAccessGroup;

public interface SfComAccessGroupRepository extends JpaRepository<SfComAccessGroup, String>{
	
}
