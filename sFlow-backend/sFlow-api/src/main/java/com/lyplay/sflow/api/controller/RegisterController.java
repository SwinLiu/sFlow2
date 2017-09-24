package com.lyplay.sflow.api.controller;

import static com.lyplay.sflow.common.dto.RestResult.fail;
import static com.lyplay.sflow.common.dto.RestResult.success;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lyplay.sflow.api.auth.AuthPassport;
import com.lyplay.sflow.common.dto.RestResult;
import com.lyplay.sflow.common.enums.ErrorCode;
import com.lyplay.sflow.common.util.PasswdUtil;
import com.lyplay.sflow.dto.RegisterUser;

/**
 * 
 * Register Rest API Functions
 * 
 * @author lyplay.com
 *
 */

@RestController
@EnableAutoConfiguration
public class RegisterController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());


	@AuthPassport(validate = false)
	@RequestMapping(value = "/api/register", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public RestResult register(RegisterUser registerUser, HttpSession session) throws Exception {
		
		if(PasswdUtil.checkCaptchaCode(session, registerUser.getCaptchaCode())){
			return fail(ErrorCode.CAPTCHA_ERROR); 
		}
		
		String pwd = PasswdUtil.getPasswd(session, registerUser.getPassword());
		if(StringUtils.isEmpty(pwd)){
			return fail(); // userAccount or Password have issue.
		}
		

		return success();

		
		

	}

	
}
