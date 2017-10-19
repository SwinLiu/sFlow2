package com.lyplay.sflow.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lyplay.sflow.data.domain.user.UserAccount;
import com.lyplay.sflow.data.domain.user.UserPassword;
import com.lyplay.sflow.data.enums.UserAccountStatus;
import com.lyplay.sflow.data.repository.UserAccountRepository;
import com.lyplay.sflow.data.repository.UserPasswordRepository;
import com.lyplay.sflow.orm.components.Pagination;
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
	public UserSession login(String userName, String password) {
		
		UserAccount userAccount = userAccountRepository.findByUserName(userName);
		
		if(userAccount == null){
			return null;
		}else{
			UserPassword userPwd = userPasswordRepository.findByUidAndPassword(userAccount.getUid(), password);
			if(userPwd != null){
				UserSession userSession = new UserSession();
				userSession.setEmail(userAccount.getEmail());
				userSession.setUid(String.valueOf(userAccount.getUid()));
				userSession.setUserName(userAccount.getUserName());
				return userSession;
			}else{
				return null;
			}
			
		}
	}
	
	@Transactional
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
	
	@Override
	public Pagination<UserViewDto> getUserListByPage(Pageable pageable) {
		// TODO add order by
		Page<UserAccount> userPageData = userAccountRepository.findAll(pageable);
		if(CollectionUtils.isNotEmpty(userPageData.getContent())){
			List<UserViewDto> userViewList = new ArrayList<>();
			for(UserAccount userAccount : userPageData.getContent()){
				UserViewDto userViewDto = new UserViewDto();
				userViewDto.setUid(String.valueOf(userAccount.getUid()));
				userViewDto.setUserName(userAccount.getUserName());
				userViewDto.setPhoneNumber(userAccount.getPhoneNumber());
				userViewDto.setEmail(userAccount.getEmail());
				userViewDto.setStatus(userAccount.getStatus());
				userViewList.add(userViewDto);
			}
			return new Pagination<UserViewDto>(userPageData.getSize(), userPageData.getTotalElements(), userPageData.getNumber() + 1, userViewList);
		} else {
			return new Pagination<UserViewDto>();
		}
	}
	
	@Transactional
	@Override
	public boolean deleteUser(String uid) {
		// TODO Auto-generated method stub
		userPasswordRepository.delete(uid);
		userAccountRepository.delete(uid);
		return true;
	}

}
