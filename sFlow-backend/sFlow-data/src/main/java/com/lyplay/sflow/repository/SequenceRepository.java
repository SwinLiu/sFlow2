package com.lyplay.sflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lyplay.sflow.domain.system.Sequence;

public interface SequenceRepository extends JpaRepository<Sequence, String>, SysSequenceRepository{
	
	Sequence findBySequenceName(String sequenceName);
	
}
