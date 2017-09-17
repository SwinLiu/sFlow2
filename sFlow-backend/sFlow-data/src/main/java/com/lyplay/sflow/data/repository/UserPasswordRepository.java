package com.lyplay.sflow.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lyplay.sflow.data.domain.user.UserPassword;

public interface UserPasswordRepository extends JpaRepository<UserPassword, String> {


}
