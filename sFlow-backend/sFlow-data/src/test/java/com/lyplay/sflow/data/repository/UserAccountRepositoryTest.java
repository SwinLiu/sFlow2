package com.lyplay.sflow.data.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lyplay.sflow.data.BaseTest;
import com.lyplay.sflow.data.domain.user.UserAccount;
import com.lyplay.sflow.data.enums.UserAccountStatus;

public class UserAccountRepositoryTest extends BaseTest{

	@Autowired
	UserAccountRepository userAccountRepository;
	
	@Test
	public void userAccountTest() {
		
		UserAccount userAccount = new UserAccount();
		userAccount.setUserName("Swin.Liu");
		userAccount.setStatus(UserAccountStatus.ACTIVE);
		userAccountRepository.save(userAccount);
		
		
	}
	
	
}
