package com.lyplay.sflow.controller;

import static com.lyplay.sflow.common.dto.RestResult.fail;
import static com.lyplay.sflow.common.dto.RestResult.success;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lyplay.sflow.auth.AuthPassport;
import com.lyplay.sflow.common.dto.RestResult;
import com.lyplay.sflow.common.enums.ErrorCode;
import com.lyplay.sflow.common.util.PasswdUtil;
import com.lyplay.sflow.dto.UserDto;
import com.lyplay.sflow.model.UserSession;
import com.lyplay.sflow.service.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@EnableAutoConfiguration
public class LoginController {

	@Autowired
	UserService userService;

	@AuthPassport(validate = false)
	@ApiOperation(value = "用户登录认证", notes = "用户帐号密码检查")
	@ApiImplicitParams({ 
			@ApiImplicitParam(name = "loginAccount", value = "用户名/邮箱", required = true, dataType = "String"),
			@ApiImplicitParam(name = "passwd", value = "用户密码", required = true, dataType = "String"),
			@ApiImplicitParam(name = "captchaCode", value = "验证码", required = true, dataType = "String")
	})
	@RequestMapping(value = "/api/login", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public RestResult login(
			@RequestParam(value = "loginAccount", required = true) String loginAccount,
			@RequestParam(value = "passwd", required = true) String passwd,
			@RequestParam(value = "captchaCode") String captchaCode,
			HttpSession session) throws Exception {

		if(!PasswdUtil.checkCaptchaCode(session, captchaCode)){
			return fail(ErrorCode.CAPTCHA_ERROR);
		}
		
		String pwd = PasswdUtil.getPasswd(session, loginAccount, passwd);
		if(StringUtils.isEmpty(pwd)){
			return fail(ErrorCode.LOGIN_ERROR); // userAccount or Password have issue.
		}
		
		UserDto userDto = new UserDto();
		
		UserSession userSession = userService.login(userDto);
		if (userSession != null) {
			session.setAttribute("userSccount", userSession);
			return success();
		} else {
			return fail(ErrorCode.LOGIN_ERROR); // userAccount or Password have issue.
		}

	}
	
	@RequestMapping(value = "/api/logout", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public RestResult logout(HttpSession session) throws Exception {
		//清除Session  
        session.invalidate();
        return success();
	}

}
