package com.lyplay.sflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lyplay.sflow.domain.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {

	

}
