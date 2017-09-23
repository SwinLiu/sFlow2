package com.lyplay.sflow.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lyplay.sflow.data.domain.user.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {

	UserAccount findByUserName(String userName);

}
