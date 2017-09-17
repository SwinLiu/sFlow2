package com.lyplay.sflow.data.domain.system;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity(name="sf_sys_sequence")
@NamedStoredProcedureQuery(name = "sys.sequence", procedureName = "nextval", parameters = {
		  @StoredProcedureParameter(mode = ParameterMode.IN, name = "sequence_name", type = String.class),
		  @StoredProcedureParameter(mode = ParameterMode.OUT, name = "prefix", type = String.class),
		  @StoredProcedureParameter(mode = ParameterMode.OUT, name = "curr_value", type = Long.class),
		  @StoredProcedureParameter(mode = ParameterMode.OUT, name = "lpad_char", type = Character.class),
		  @StoredProcedureParameter(mode = ParameterMode.OUT, name = "lpad_length", type = Integer.class),
		  @StoredProcedureParameter(mode = ParameterMode.OUT, name = "suffix", type = String.class) })
public class Sequence implements Serializable{
	
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

	@Column(name = "lpad_length", length = 2)
	private Integer lpadLength;
	
	@Column(name = "lpad_char", length = 1)
	private Character lpadChar;

	@Column(name = "increment", length = 2)
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

	public Character getLpadChar() {
		return lpadChar;
	}

	public void setLpadChar(Character lpadChar) {
		this.lpadChar = lpadChar;
	}

	public Integer getIncrement() {
		return increment;
	}

	public void setIncrement(Integer increment) {
		this.increment = increment;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}

