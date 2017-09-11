package com.lyplay.sflow.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import com.lyplay.sflow.domain.system.Sequence;
import com.lyplay.sflow.repository.SysSequenceRepository;


public class SequenceRepositoryImpl implements SysSequenceRepository {

	@PersistenceContext 
	private EntityManager em;

	@Override
	public Sequence nextval(String sequenceName) {
		Sequence seq = new Sequence();
		seq.setSequenceName(sequenceName);
		
		StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("sys.sequence");
		proc.setParameter("sequence_name", sequenceName);
		proc.execute();
		seq.setPrefix((String) proc.getOutputParameterValue("prefix"));
		seq.setCurrValue((Long) proc.getOutputParameterValue("curr_value"));	
		seq.setLpadChar((Character) proc.getOutputParameterValue("lpad_char"));
		seq.setLpadLength((Integer) proc.getOutputParameterValue("lpad_length"));
		seq.setSuffix((String) proc.getOutputParameterValue("suffix"));
		return seq;
	}
	

}
