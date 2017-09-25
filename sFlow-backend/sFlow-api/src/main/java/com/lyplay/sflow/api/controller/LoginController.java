package com.lyplay.sflow.api.controller;

import static com.lyplay.sflow.common.dto.RestResult.fail;
import static com.lyplay.sflow.common.dto.RestResult.success;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lyplay.sflow.api.auth.AuthPassport;
import com.lyplay.sflow.api.dto.UserParam;
import com.lyplay.sflow.common.dto.RestResult;
import com.lyplay.sflow.common.enums.ErrorCode;
import com.lyplay.sflow.common.util.Constant;
import com.lyplay.sflow.common.util.PasswdUtil;
import com.lyplay.sflow.common.util.RSAUtil;
import com.lyplay.sflow.common.util.TokenUtil;
import com.lyplay.sflow.service.CacheService;
import com.lyplay.sflow.service.UserService;
import com.lyplay.sflow.service.model.UserSession;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@EnableAutoConfiguration
public class LoginController {

	@Autowired
	UserService userService;

	@Autowired
	CacheService cacheService;
	
	@AuthPassport(validate = false)
	@ApiOperation(value = "用户登录认证", notes = "用户帐号密码检查")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userParam", value = "userParam", required = true)
	})
	@RequestMapping(value = "/api/login", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public RestResult login(@RequestBody UserParam userParam) throws Exception {

		String captchaCode = cacheService.getString(userParam.getCaptchaCodeId());
		if(StringUtils.isEmpty(captchaCode)){
			return fail("Please reflush captcha code."); 
		}
		if(!StringUtils.equals(StringUtils.lowerCase(captchaCode), StringUtils.lowerCase(userParam.getCaptchaCode()))){
			return fail(ErrorCode.CAPTCHA_ERROR); 
		}
		
		String privateRsaKey = cacheService.getString(RSAUtil.PRIVATE_KEY + "_" + userParam.getRsaKeyId());
		String pwd = PasswdUtil.getPasswd(privateRsaKey, userParam.getUserName(), userParam.getPassword());
		if(StringUtils.isEmpty(pwd)){
			return fail(ErrorCode.LOGIN_ERROR); // userAccount or Password have issue.
		}
		
		UserSession userSession = userService.login(userParam.getUserName(), pwd);
		if (userSession != null) {
			Map<String, Object> claims = new HashMap<String, Object>(1);
			claims.put(Constant.USER_ID, userSession.getUid());
			claims.put(Constant.USER_NAME, userSession.getUserName());
			String token = TokenUtil.getJWTString(claims, null);
			userSession.setJwtToken(token);
			cacheService.setObject(token, userSession);
			return success(userSession);
		} else {
			return fail(ErrorCode.LOGIN_ERROR); // userAccount or Password have issue.
		}

	}
	
	@RequestMapping(value = "/api/logout", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public RestResult logout() throws Exception {
        return success();
	}

}
