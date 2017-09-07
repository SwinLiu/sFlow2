package com.lyplay.sflow.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lyplay.sflow.BaseTest;
import com.lyplay.sflow.domain.UserAccount;

public class UserAccountRepositoryTest extends BaseTest{

	@Autowired
	UserAccountRepository userAccountRepository;
	
	@Test
	public void userAccountTest() {
		
		UserAccount userAccount = new UserAccount();
		userAccount.setUserName("Swin.Liu");
		userAccountRepository.save(userAccount);
		
		
	}
	
	
}
