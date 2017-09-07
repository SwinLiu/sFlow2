package com.lyplay.sflow.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lyplay.sflow.BaseTest;
import com.lyplay.sflow.domain.system.Sequence;

public class SequenceRepositoryTest extends BaseTest{

	@Autowired
	SequenceRepository sequenceRepository;
	
	@Test
	public void sequenceTest() {
		
		Sequence sequence = new Sequence();
		sequence.setSequenceName("SEQ_USER");
		sequence.setPrefix("U");
		sequence.setLpadLength(5);
		sequence.setLpadChar('0');
		sequence.setCurrValue(0l);
		sequence.setIncrement(1);
		
		sequenceRepository.save(sequence);
		
		
	}
	
	
}
