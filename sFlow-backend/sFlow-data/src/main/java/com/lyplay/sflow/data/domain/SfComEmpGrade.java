package com.lyplay.sflow.data.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.lyplay.sflow.data.util.SFlowKeyGenerator;

@Entity(name="sf_com_emp_grade")
public class SfComEmpGrade implements Serializable {

	private static final long serialVersionUID = 4248095165334754392L;

	@Id	
	@GeneratedValue(generator="sFlowKeyGenerator")
	@GenericGenerator(name = "sFlowKeyGenerator", strategy = "com.lyplay.sflow.data.util.SFlowKeyGenerator",
			parameters = { @Parameter(name = SFlowKeyGenerator.SEQUENCE_NAME, value = "SEQ_SF_COM_EMP_GRADE") })
	private String id;
	
	@Column(name = "emp_id")
	private String empId;
	
	@Column(name = "grade_id")
	private String gradeId;
	  
	@Column(name = "create_time")
    private Long createTime; 
	
	@Column(name = "changer")
	private String changer;
	
	@Column(name = "update_time")
	private Long updateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getChanger() {
		return changer;
	}

	public void setChanger(String changer) {
		this.changer = changer;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}
	
	@PreUpdate
    public void preUpdate() {
    	updateTime = (new Date()).getTime();
    }
    
    @PrePersist
    public void prePersist() {
        Date now = new Date();
        createTime = now.getTime();
        updateTime = now.getTime();
    }
    
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
