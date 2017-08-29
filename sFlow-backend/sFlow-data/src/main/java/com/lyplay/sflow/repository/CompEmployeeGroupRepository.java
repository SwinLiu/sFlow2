package com.lyplay.sflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lyplay.sflow.domain.CompEmployeeGroup;
import com.lyplay.sflow.pk.CompEmployeeGroupPK;

public interface CompEmployeeGroupRepository extends JpaRepository<CompEmployeeGroup, CompEmployeeGroupPK> {

	CompEmployeeGroup findById(CompEmployeeGroupPK id);

}
