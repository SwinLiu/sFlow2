package com.lyplay.sflow.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lyplay.sflow.data.domain.Company;
import com.lyplay.sflow.data.domain.SfComOrg;
import com.lyplay.sflow.data.repository.CompEmployeeGroupRepository;
import com.lyplay.sflow.data.repository.CompanyRepository;
import com.lyplay.sflow.data.repository.EmployeeRepository;
import com.lyplay.sflow.data.repository.SfComAccessGroupRepository;
import com.lyplay.sflow.data.repository.SfComGradeRepository;
import com.lyplay.sflow.data.repository.SfComOrgRepository;
import com.lyplay.sflow.data.repository.SfComOrgTypeRepository;
import com.lyplay.sflow.data.repository.SfComPosRepository;
import com.lyplay.sflow.data.repository.SfComRoleRepository;
import com.lyplay.sflow.orm.components.Pagination;
import com.lyplay.sflow.service.CompanyService;
import com.lyplay.sflow.service.dto.AccessGroupDto;
import com.lyplay.sflow.service.dto.CompanyDto;
import com.lyplay.sflow.service.dto.GradeDto;
import com.lyplay.sflow.service.dto.OrgTypeDto;
import com.lyplay.sflow.service.dto.OrganizationDto;
import com.lyplay.sflow.service.dto.PositionDto;
import com.lyplay.sflow.service.dto.RoleDto;
import com.lyplay.sflow.service.dto.ValueLabelBean;

@Service
public class CompanyServiceImpl implements CompanyService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CompanyRepository companyRepository;
	@Autowired
	CompEmployeeGroupRepository compEmployeeGroupRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	SfComOrgRepository sfComOrgRepository;
	@Autowired
	SfComOrgTypeRepository sfComOrgTypeRepository;
	@Autowired
	SfComRoleRepository sfComRoleRepository;
	@Autowired
	SfComGradeRepository sfComGradeRepository;
	@Autowired
	SfComAccessGroupRepository sfComAccessGroupRepository;
	@Autowired
	SfComPosRepository sfComPosRepository;
	
	
	@Override
	public boolean createCompany(CompanyDto companyInfo) {
		Company company = new Company();
		company.setCompanyName(companyInfo.getCompanyName());
		company.setAddress(companyInfo.getAddress());
		company.setChanger(companyInfo.getChanger());
		companyRepository.save(company);
		return true;
	}
	
	@Override
	public List<CompanyDto> getCompanyList() {
		Sort sort = new Sort(Direction.ASC, "comp_id");
		List<Company> compList = companyRepository.findAll(sort);
		if(CollectionUtils.isNotEmpty(compList)){
			List<CompanyDto> compDtoList = new ArrayList<CompanyDto>();
			for(Company company : compList){
				CompanyDto companyDto = new CompanyDto();
				try {
					BeanUtils.copyProperties(companyDto, company);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
				compDtoList.add(companyDto);
			}
			return compDtoList;
		} else {
			return Collections.emptyList();
		}
	}
	
	@Override
	public Pagination<CompanyDto> getCompanyListByPage(Pageable pageable) {
		Page<Company> compPageData = companyRepository.findAll(pageable);
		if(CollectionUtils.isNotEmpty(compPageData.getContent())){
			List<CompanyDto> compDtoList = new ArrayList<CompanyDto>(compPageData.getContent().size());
			for(Company company : compPageData.getContent()){
				CompanyDto companyDto = new CompanyDto();
				try {
					BeanUtils.copyProperties(companyDto, company);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
				compDtoList.add(companyDto);
			}
			return new Pagination<CompanyDto>(compPageData.getSize(), compPageData.getTotalElements(), compPageData.getNumber() + 1, compDtoList);
		} else {
			return new Pagination<CompanyDto>();
		}
	}
	
	@Override
	public List<ValueLabelBean> getCompanyDropdownList(String uid) {
		//TODO user mapping data & order by condition
		List<Company> compList = companyRepository.findAll();
		if(CollectionUtils.isNotEmpty(compList)){
			List<ValueLabelBean> vlBeanList = new ArrayList<ValueLabelBean>();
			for(Company company : compList){
				ValueLabelBean vlBean = new ValueLabelBean();
				vlBean.setValue(company.getCompId());
				vlBean.setLabel(company.getCompanyName());
				vlBeanList.add(vlBean);
			}
			return vlBeanList;
		} else {
			return Collections.emptyList();
		}
	}
	
	@Transactional
	@Override
	public boolean deleteCompany(String compId) {
		employeeRepository.deleteByCompId(compId);
		compEmployeeGroupRepository.deleteCompany(compId);
		companyRepository.delete(compId);
		return true;
	}

	@Override
	public boolean addOrganization(OrganizationDto orgDto) {
		SfComOrg sfComOrg = new SfComOrg();
		try {
			BeanUtils.copyProperties(sfComOrg, orgDto);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		sfComOrgRepository.save(sfComOrg);
		return true;
	}

	@Override
	public List<OrganizationDto> getOrgList(String compId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pagination<OrganizationDto> getOrgListByPage(String compId, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteOrg(String orgId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addOrgType(OrgTypeDto orgTypeDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<OrgTypeDto> getOrgTypeList(String compId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pagination<OrgTypeDto> getOrgTypeListByPage(String compId, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteOrgType(String orgTypeId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addJobGrade(GradeDto gradeDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<GradeDto> getJobGradeList(String compId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pagination<GradeDto> getJobGradeListByPage(String compId, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteJobGrade(String gradeId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addPosition(PositionDto positionDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<PositionDto> getPositionList(String compId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pagination<PositionDto> getPositionListByPage(String compId, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deletePosition(String positionId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAccessGroup(AccessGroupDto accessGrpDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<AccessGroupDto> getAccessGroupList(String compId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pagination<AccessGroupDto> getAccessGroupListByPage(String compId, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteAccessGroup(String accessGrpId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addRole(RoleDto roleDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<RoleDto> getRoleList(String compId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pagination<RoleDto> getRoleListByPage(String compId, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteRole(String roleId) {
		// TODO Auto-generated method stub
		return false;
	}

}
