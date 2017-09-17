package com.lyplay.sflow.api.controller;

import static com.lyplay.sflow.common.dto.RestResult.fail;
import static com.lyplay.sflow.common.dto.RestResult.success;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lyplay.sflow.common.dto.RestResult;
import com.lyplay.sflow.common.util.Constant;
import com.lyplay.sflow.service.UserService;
import com.lyplay.sflow.service.dto.UserDto;
import com.lyplay.sflow.service.model.UserSession;

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

	@Autowired
	UserService userService;

	@RequestMapping(value = "/api/user/add", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public RestResult register(UserDto userDto, HttpSession session) throws Exception {
		UserSession userSession = (UserSession) session.getAttribute(Constant.USER_SESSION);
		if(userSession == null){
			logger.error("No user session data.");
			return fail("No user session data.");
		}
		userDto.setChanger(userSession.getUid());
		userService.addNewUser(userDto);
		return success();
	}
	
	@RequestMapping(value = "/api/user/list", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public RestResult getUserList() throws Exception {
		return success(userService.getUserList());
	}
	

	
}
