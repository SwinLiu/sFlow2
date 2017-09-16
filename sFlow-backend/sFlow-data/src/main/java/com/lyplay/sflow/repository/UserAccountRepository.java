package com.lyplay.sflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lyplay.sflow.domain.user.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {

	

}
