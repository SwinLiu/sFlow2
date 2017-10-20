package com.lyplay.sflow.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lyplay.sflow.data.domain.system.Menu;

public interface MenuRepository extends JpaRepository<Menu, String>{
	
}
