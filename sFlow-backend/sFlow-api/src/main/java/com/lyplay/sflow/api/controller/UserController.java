package com.lyplay.sflow.api.controller;

import static com.lyplay.sflow.common.dto.RestResult.fail;
import static com.lyplay.sflow.common.dto.RestResult.success;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lyplay.sflow.api.dto.AppDescription;
import com.lyplay.sflow.common.dto.RestResult;
import com.lyplay.sflow.service.MenuService;
import com.lyplay.sflow.service.UserService;
import com.lyplay.sflow.service.dto.MenuTree;
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
	@Autowired
	MenuService menuService;

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
	
	@RequestMapping(value = "/api/user/page", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public RestResult getUserPageData(@RequestParam(value = "page", defaultValue = "1") Integer page,
	        @RequestParam(value = "size", defaultValue = "10") Integer size) throws Exception {
		Sort sort = new Sort(Direction.ASC, "uid");
	    Pageable pageable = new PageRequest(page - 1, size, sort);
		return success(userService.getUserListByPage(pageable));
	}
	
	@RequestMapping(value = "/api/user/delete/{uid}", method = RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	public RestResult deleteUser(@PathVariable(required = true)  String uid) throws Exception {
		if(StringUtils.equals(uid, "U00001")){
			return fail("Unable to delete Admin account.");
		}
		if(userService.deleteUser(uid)){
			return success();
		} else {
			return fail("Delete failed.");
		}
	}
	
	@RequestMapping(value = "/api/user/session", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public RestResult getUserSessionData() throws Exception {
		Map<String, Object> result = new HashMap<>();
		UserSession userSession = UserSessionContext.getUserSession();
		result.put("app", new AppDescription());
		result.put("user", userSession);
		List<MenuTree> menuList = menuService.getFullMenuList();
		result.put("menu", menuList);
		return success(result);
	}
	
}
