package com.lyplay.sflow.service;

import com.lyplay.sflow.dto.UserDto;
import com.lyplay.sflow.model.UserSession;

public interface UserService {

	UserSession login(UserDto userDto);
	
	boolean addNewUser(UserDto userDto);
	
}
