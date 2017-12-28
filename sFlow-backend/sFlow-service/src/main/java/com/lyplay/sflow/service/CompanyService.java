package com.lyplay.sflow.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.lyplay.sflow.orm.components.Pagination;
import com.lyplay.sflow.service.dto.AccessGroupDto;
import com.lyplay.sflow.service.dto.CompanyDto;
import com.lyplay.sflow.service.dto.GradeDto;
import com.lyplay.sflow.service.dto.OrgTypeDto;
import com.lyplay.sflow.service.dto.OrganizationDto;
import com.lyplay.sflow.service.dto.PositionDto;
import com.lyplay.sflow.service.dto.RoleDto;
import com.lyplay.sflow.service.dto.ValueLabelBean;

public interface CompanyService {

	boolean createCompany(CompanyDto companyInfo);
	
	List<CompanyDto> getCompanyList();
	
	Pagination<CompanyDto> getCompanyListByPage(Pageable pageable);

	List<ValueLabelBean> getCompanyDropdownList(String uid);
	
	boolean deleteCompany(String compId);
	
	/**
	 * Organization methods
	 */
	boolean addOrganization(OrganizationDto orgDto);
	
	List<OrganizationDto> getOrgList(String compId);
	
	Pagination<OrganizationDto> getOrgListByPage(String compId, Pageable pageable);
	
	boolean deleteOrg(String orgId);
	
	/**
	 * Organization type methods
	 */
	boolean addOrgType(OrgTypeDto orgTypeDto);
	
	List<OrgTypeDto> getOrgTypeList(String compId);
	
	Pagination<OrgTypeDto> getOrgTypeListByPage(String compId, Pageable pageable);
	
	boolean deleteOrgType(String orgTypeId);
	
	/**
	 * Job grade methods
	 */
	boolean addJobGrade(GradeDto gradeDto);
	
	List<GradeDto> getJobGradeList(String compId);
	
	Pagination<GradeDto> getJobGradeListByPage(String compId, Pageable pageable);
	
	boolean deleteJobGrade(String gradeId);
	
	/**
	 * Position methods
	 */
	boolean addPosition(PositionDto positionDto);
	
	List<PositionDto> getPositionList(String compId);
	
	Pagination<PositionDto> getPositionListByPage(String compId, Pageable pageable);
	
	boolean deletePosition(String positionId);
	
	/**
	 * Access group methods
	 */
	boolean addAccessGroup(AccessGroupDto accessGrpDto);
	
	List<AccessGroupDto> getAccessGroupList(String compId);
	
	Pagination<AccessGroupDto> getAccessGroupListByPage(String compId, Pageable pageable);
	
	boolean deleteAccessGroup(String accessGrpId);
	
	/**
	 * Role methods
	 */
	boolean addRole(RoleDto roleDto);
	
	List<RoleDto> getRoleList(String compId);
	
	Pagination<RoleDto> getRoleListByPage(String compId, Pageable pageable);
	
	boolean deleteRole(String roleId);
	
}
