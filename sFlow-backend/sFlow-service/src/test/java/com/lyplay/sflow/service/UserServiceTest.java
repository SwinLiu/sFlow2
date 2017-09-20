package com.lyplay.sflow.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lyplay.sflow.BaseTest;
import com.lyplay.sflow.service.dto.UserDto;

public class UserServiceTest extends BaseTest{

	@Autowired
	UserService userService;
	
	@Test
	public void addNewUserTest() {
		
		UserDto userDto = new UserDto();
		
		userDto.setChanger("Test");
		userDto.setEmail("i@lyplay.com");
		userDto.setPassword("Test1234");
		userDto.setPhoneNumber("1234567890");
		userDto.setUserName("Swin.Liu");
		
		userService.addNewUser(userDto);
	}
}
