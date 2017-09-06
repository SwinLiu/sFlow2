package com.lyplay.sflow.domain.system;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.lyplay.sflow.model.BaseDomain;

@Entity(name="sf_sys_sequence")
public class Sequence extends BaseDomain implements Serializable{
	
	private static final long serialVersionUID = -6805723584886767992L;

	@Id
	@Column(name = "sequence_name", nullable = false, length = 20)
	private String sequenceName;

	@Column(name = "sequence_name", nullable = false, length = 20)
	private String prefix;

	@Column(length = 20, unique = true, nullable = false)
	private Long currValue;

	@Column(length = 20)
	private String suffix;

	@Column(length = 3, nullable = false)
	private Integer lpadLength;
	
	@Column(length = 1, nullable = false)
	private char lpadChar;

	@Column(length = 3, nullable = false)
	private Integer increment;
	
	
}

