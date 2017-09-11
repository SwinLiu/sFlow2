package com.lyplay.sflow.repository;

import com.lyplay.sflow.domain.system.Sequence;


public interface SysSequenceRepository {
	
	Sequence nextval(String sequenceName);
}
