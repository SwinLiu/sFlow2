package com.lyplay.sflow.data.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lyplay.sflow.data.BaseTest;
import com.lyplay.sflow.data.domain.system.Sequence;

public class SequenceRepositoryTest extends BaseTest{

	@Autowired
	SequenceRepository sequenceRepository;
	
	@Test
	public void sequenceTest() {
		
		Sequence sequence = new Sequence();
		sequence.setSequenceName("SEQ_USER2");
		sequence.setPrefix("U");
		sequence.setLpadLength(5);
		sequence.setLpadChar('0');
		sequence.setCurrValue(0l);
		sequence.setIncrement(1);
		
		sequenceRepository.save(sequence);
		
	}
	
	@Test
	public void nextValTest() {
		
		Sequence seq = sequenceRepository.nextval("SEQ_USER");
		
		System.out.println(seq.getSequenceName());
		System.out.println(seq.getPrefix());
		System.out.println(seq.getCurrValue());
		System.out.println(seq.getLpadChar());
		System.out.println(seq.getLpadLength());
		System.out.println(seq.getSuffix());
		
	}
	
	
}
