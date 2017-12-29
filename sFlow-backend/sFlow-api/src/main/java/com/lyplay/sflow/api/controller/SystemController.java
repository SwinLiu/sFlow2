package com.lyplay.sflow.api.controller;

import static com.lyplay.sflow.common.dto.RestResult.success;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lyplay.sflow.common.dto.RestResult;
import com.lyplay.sflow.service.MenuService;

/**
 * 
 * System Rest API Functions
 * 
 * @author lyplay.com
 *
 */

@RestController
@EnableAutoConfiguration
public class SystemController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MenuService menuService;

	@RequestMapping(value = "/api/sys/menu/list", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public RestResult getMenuList() throws Exception {
		return success(menuService.getFullMenuList());
	}
	
}
