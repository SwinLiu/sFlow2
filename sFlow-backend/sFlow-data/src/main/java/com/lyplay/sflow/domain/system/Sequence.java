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

	@Column(name = "prefix", length = 10)
	private String prefix;

	@Column(name = "curr_value", length = 10, nullable = false)
	private Long currValue;

	@Column(name = "suffix", length = 10)
	private String suffix;

	@Column(name = "lpad_length", length = 3)
	private Integer lpadLength;
	
	@Column(name = "lpad_char", length = 1)
	private char lpadChar;

	@Column(name = "increment", length = 3)
	private Integer increment;

	public String getSequenceName() {
		return sequenceName;
	}

	public void setSequenceName(String sequenceName) {
		this.sequenceName = sequenceName;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public Long getCurrValue() {
		return currValue;
	}

	public void setCurrValue(Long currValue) {
		this.currValue = currValue;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public Integer getLpadLength() {
		return lpadLength;
	}

	public void setLpadLength(Integer lpadLength) {
		this.lpadLength = lpadLength;
	}

	public char getLpadChar() {
		return lpadChar;
	}

	public void setLpadChar(char lpadChar) {
		this.lpadChar = lpadChar;
	}

	public Integer getIncrement() {
		return increment;
	}

	public void setIncrement(Integer increment) {
		this.increment = increment;
	}
	
}

