package com.lyplay.sflow.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.lyplay.sflow.orm.components.Pagination;
import com.lyplay.sflow.service.dto.UserDto;
import com.lyplay.sflow.service.dto.UserViewDto;
import com.lyplay.sflow.service.model.UserSession;

public interface UserService {

	UserSession login(String userName, String password);
	
	boolean addNewUser(UserDto userDto);
	
	List<UserViewDto> getUserList();
	
	Pagination<UserViewDto> getUserListByPage(Pageable pageable);
	
	boolean deleteUser(String uid);
	
}
