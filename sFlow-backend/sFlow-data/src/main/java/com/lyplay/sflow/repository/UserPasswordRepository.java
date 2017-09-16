package com.lyplay.sflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lyplay.sflow.domain.user.UserPassword;

public interface UserPasswordRepository extends JpaRepository<UserPassword, String> {


}
