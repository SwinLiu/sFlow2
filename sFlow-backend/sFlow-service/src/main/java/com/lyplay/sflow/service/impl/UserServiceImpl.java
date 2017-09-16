package com.lyplay.sflow.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyplay.sflow.domain.user.UserAccount;
import com.lyplay.sflow.domain.user.UserPassword;
import com.lyplay.sflow.dto.UserDto;
import com.lyplay.sflow.enums.UserAccountStatus;
import com.lyplay.sflow.model.UserSession;
import com.lyplay.sflow.repository.UserAccountRepository;
import com.lyplay.sflow.repository.UserPasswordRepository;
import com.lyplay.sflow.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserAccountRepository userAccountRepository;
	
	@Autowired
	UserPasswordRepository userPasswordRepository;
	
	@Override
	public UserSession login(UserDto userDto) {
		// TODO Auto-generated method stub
		
		return null;
	}
	
	@Override
	public boolean addNewUser(UserDto userDto) {
		
		UserAccount userAccount = new UserAccount();
		userAccount.setUserName(userDto.getUserName());
		userAccount.setPhoneNumber(userDto.getPhoneNumber());
		userAccount.setEmail(userDto.getEmail());
		userAccount.setChanger(userDto.getChanger());
		userAccount.setStatus(StringUtils.isEmpty(userDto.getPassword()) ? UserAccountStatus.INACTIVE : UserAccountStatus.ACTIVE);
		userAccountRepository.save(userAccount);
		
		if(StringUtils.isNotEmpty(userDto.getPassword())){
			UserPassword userPassword = new UserPassword();
			userPassword.setUid(userAccount.getId());
			userPassword.setChanger(userDto.getChanger());
			userPassword.setPassword(userDto.getPassword());
			userPasswordRepository.save(userPassword);
		}
		
		return true;
	}

}
