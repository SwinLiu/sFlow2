package com.lyplay.sflow.controller;

import static com.lyplay.sflow.common.dto.RestResult.success;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lyplay.sflow.common.dto.RestResult;

/**
 * 
 * Register Rest API Functions
 * 
 * @author lyplay.com
 *
 */

@RestController
@EnableAutoConfiguration
public class UserController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());


	@RequestMapping(value = "/api/user/add", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public RestResult register(HttpSession session) throws Exception {
		session.setAttribute("url", "www.lyplay.com");
		return success();
	}

	
}
