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

@Entity(name="sf_com_user_access_grp")
public class SfComUserAccessGroup implements Serializable {

	private static final long serialVersionUID = -6056291288465811603L;

	@Id	
	@GeneratedValue(generator="sFlowKeyGenerator")
	@GenericGenerator(name = "sFlowKeyGenerator", strategy = "com.lyplay.sflow.data.util.SFlowKeyGenerator",
			parameters = { @Parameter(name = SFlowKeyGenerator.SEQUENCE_NAME, value = "SEQ_SF_COM_USER_ACCESS_GRP") })
	private String id;
	
	@Column(name = "comp_id")
	private String compId;
	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "group_id")
	private String groupId;
	
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

	public String getCompId() {
		return compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
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
