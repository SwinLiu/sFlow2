package com.lyplay.sflow.service;

import java.util.List;

import com.lyplay.sflow.service.dto.UserDto;
import com.lyplay.sflow.service.dto.UserViewDto;
import com.lyplay.sflow.service.model.UserSession;

public interface UserService {

	UserSession login(String userName, String password);
	
	boolean addNewUser(UserDto userDto);
	
	List<UserViewDto> getUserList();
	
	boolean deleteUser(String uid);
	
}
