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
import com.lyplay.sflow.service.UserService;
import com.lyplay.sflow.service.dto.UserDto;
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
public class UserController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserService userService;

	@RequestMapping(value = "/api/user/add", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public RestResult register(@RequestBody UserDto userDto) throws Exception {
		UserSession userSession = UserSessionContext.getUserSession();
		userDto.setChanger(userSession.getUid());
		
//		String pwd = PasswdUtil.getPasswd(session, userDto.getUserName(), userDto.getPassword());
//		if(StringUtils.isEmpty(pwd)){
//			return fail(ErrorCode.LOGIN_ERROR); // userAccount or Password have issue.
//		} else {
//			userDto.setPassword(pwd);
//		}
		
		try {
			userService.addNewUser(userDto);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return fail("Save failed.");
		}
		return success();
	}
	
	@RequestMapping(value = "/api/user/list", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public RestResult getUserList() throws Exception {
		return success(userService.getUserList());
	}
	

	
}
