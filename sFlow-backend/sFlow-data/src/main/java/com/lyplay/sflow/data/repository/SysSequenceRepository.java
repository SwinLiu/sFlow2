package com.lyplay.sflow.data.repository;

import com.lyplay.sflow.data.domain.system.Sequence;


public interface SysSequenceRepository {
	
	Sequence nextval(String sequenceName);
}
