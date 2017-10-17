package com.lyplay.sflow.api.controller;

import static com.lyplay.sflow.common.dto.RestResult.fail;
import static com.lyplay.sflow.common.dto.RestResult.success;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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

import com.lyplay.sflow.api.dto.AppDescription;
import com.lyplay.sflow.api.dto.MenuTree;
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
		List<MenuTree> menuList = new ArrayList<MenuTree>();
		
		MenuTree main = new MenuTree();
		main.setText("主导航");
		main.setTranslate("main_navigation");
		main.setGroup(true);
		
		MenuTree dashboard = new MenuTree();
		dashboard.setText("仪表盘");
		dashboard.setTranslate("dashboard");
		dashboard.setGroup(false);
		dashboard.setLink("/dashboard/v1");
		dashboard.setIcon("icon-speedometer");
		main.addChildMenu(dashboard);
		
		MenuTree userProfile = new MenuTree();
		userProfile.setText("User Profile");
		userProfile.setTranslate("profile");
		userProfile.setGroup(false);
		userProfile.setLink("/user/profile");
		userProfile.setIcon("icon-speedometer");
		main.addChildMenu(userProfile);
		
		MenuTree userManagement = new MenuTree();
		userManagement.setText("User Management");
		userManagement.setTranslate("user-management");
		userManagement.setGroup(false);
		userManagement.setLink("/user/management");
		userManagement.setIcon("icon-user");
		main.addChildMenu(userManagement);
		
		MenuTree companyManagement = new MenuTree();
		companyManagement.setText("Company Management");
		companyManagement.setTranslate("company-management");
		companyManagement.setGroup(false);
		companyManagement.setLink("/company/management");
		companyManagement.setIcon("icon-speedometer");
		main.addChildMenu(companyManagement);
		
		MenuTree employeeManagement = new MenuTree();
		employeeManagement.setText("Employee Management");
		employeeManagement.setTranslate("employee-management");
		employeeManagement.setGroup(false);
		employeeManagement.setLink("/employee/management");
		employeeManagement.setIcon("icon-speedometer");
		main.addChildMenu(employeeManagement);
		
		menuList.add(main);
		
//		{
//	        "text": "主导航",
//	        "translate": "main_navigation",
//	        "group": true,
//	        "children": [{
//	            "text": "仪表盘",
//	            "translate": "dashboard",
//	            "link": "/dashboard",
//	            "icon": "icon-speedometer",
//	            "children": [{
//	                "text": "仪表盘V1",
//	                "link": "/dashboard/v1",
//	                "translate": "dashboard_v1"
//	            }]
//	        }, {
//	            "text": "小部件",
//	            "translate": "widgets",
//	            "link": "/widgets",
//	            "icon": "icon-grid",
//	            "badge": 2
//	        }]
//	    }
//		
//		
		result.put("menu", menuList);
		return success(result);
	}
	
}
