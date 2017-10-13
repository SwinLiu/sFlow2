package com.lyplay.sflow.api.controller;

import static com.lyplay.sflow.common.dto.RestResult.fail;
import static com.lyplay.sflow.common.dto.RestResult.success;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lyplay.sflow.common.dto.RestResult;
import com.lyplay.sflow.service.EmployeeService;
import com.lyplay.sflow.service.dto.EmployeeDto;
import com.lyplay.sflow.service.model.UserSession;
import com.lyplay.sflow.service.model.UserSessionContext;

@RestController
@EnableAutoConfiguration
public class EmployeeController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value = "/api/employee/add", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public RestResult addEmployee(@RequestBody EmployeeDto employeeDto) throws Exception {
		UserSession userSession = UserSessionContext.getUserSession();
		employeeDto.setChanger(userSession.getUid());
		try {
			employeeService.addEmployee(employeeDto);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return fail("Save failed.");
		}
		return success();

	}
	
	@RequestMapping(value = "/api/employee/list", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public RestResult getCompanyInfoList(String compId) throws Exception {
		return success(employeeService.getEmployeeList(compId));
	}
	
	@RequestMapping(value = "/api/employee/delete/{empId}", method = RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	public RestResult deleteEmployee(@PathVariable(required = true) String empId) throws Exception {
		if(employeeService.deleteEmployee(empId)){
			return success();
		} else {
			return fail("Delete failed.");
		}

	}

}
