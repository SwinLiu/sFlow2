package com.lyplay.sflow.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyplay.sflow.data.domain.user.UserAccount;
import com.lyplay.sflow.data.domain.user.UserPassword;
import com.lyplay.sflow.data.enums.UserAccountStatus;
import com.lyplay.sflow.data.repository.UserAccountRepository;
import com.lyplay.sflow.data.repository.UserPasswordRepository;
import com.lyplay.sflow.service.UserService;
import com.lyplay.sflow.service.dto.UserDto;
import com.lyplay.sflow.service.dto.UserViewDto;
import com.lyplay.sflow.service.model.UserSession;

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
			userPassword.setUid(String.valueOf(userAccount.getUid()));
			userPassword.setChanger(userDto.getChanger());
			userPassword.setPassword(userDto.getPassword());
			userPasswordRepository.save(userPassword);
		}
		
		return true;
	}

	@Override
	public List<UserViewDto> getUserList() {
		List<UserAccount> userAccountList = userAccountRepository.findAll();
		if(CollectionUtils.isNotEmpty(userAccountList)){
			List<UserViewDto> userViewList = new ArrayList<>();
			for(UserAccount userAccount : userAccountList){
				UserViewDto userViewDto = new UserViewDto();
				userViewDto.setUid(String.valueOf(userAccount.getUid()));
				userViewDto.setUserName(userAccount.getUserName());
				userViewDto.setPhoneNumber(userAccount.getPhoneNumber());
				userViewDto.setEmail(userAccount.getEmail());
				userViewDto.setStatus(userAccount.getStatus());
				userViewList.add(userViewDto);
			}
			return userViewList;
		} else {
			return Collections.emptyList();
		}
	}

}
