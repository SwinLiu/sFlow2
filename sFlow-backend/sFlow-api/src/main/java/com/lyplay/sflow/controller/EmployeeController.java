package com.lyplay.sflow.controller;

import static com.lyplay.sflow.common.dto.RestResult.success;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lyplay.sflow.common.dto.RestResult;
import com.lyplay.sflow.service.UserAccountService;

@RestController
@EnableAutoConfiguration
public class EmployeeController {

	@Autowired
	UserAccountService userAccountService;

	@RequestMapping(value = "/api/employee/add", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public RestResult addEmployee(HttpSession session) throws Exception {


		return success();

	}

}
