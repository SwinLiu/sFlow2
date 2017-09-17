package com.lyplay.sflow.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lyplay.sflow.data.domain.CompEmployeeGroup;
import com.lyplay.sflow.data.pk.CompEmployeeGroupPK;

public interface CompEmployeeGroupRepository extends JpaRepository<CompEmployeeGroup, CompEmployeeGroupPK> {

	CompEmployeeGroup findById(CompEmployeeGroupPK id);

}
