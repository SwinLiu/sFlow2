package com.lyplay.sflow.api.controller;

import static com.lyplay.sflow.common.dto.RestResult.fail;
import static com.lyplay.sflow.common.dto.RestResult.success;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lyplay.sflow.common.dto.RestResult;
import com.lyplay.sflow.service.CompanyService;
import com.lyplay.sflow.service.dto.CompanyDto;
import com.lyplay.sflow.service.model.UserSession;
import com.lyplay.sflow.service.model.UserSessionContext;

/**
 * 
 * Register Rest API Functions
 * 
 * @author lyplay.com
 *
 */

@RestController
@EnableAutoConfiguration
public class CompanyController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CompanyService companyService;

	@RequestMapping(value = "/api/company/add", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public RestResult register(@RequestBody CompanyDto companyDto) throws Exception {
		UserSession userSession = UserSessionContext.getUserSession();
		companyDto.setChanger(userSession.getUid());
		try {
			companyService.createCompany(companyDto);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return fail("Save failed.");
		}
		return success();
	}
	
	@RequestMapping(value = "/api/company/list", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public RestResult getUserList() throws Exception {
		return success(companyService.getCompanyList());
	}
	

	
}
