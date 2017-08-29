package com.lyplay.sflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lyplay.sflow.domain.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

	Company findById(Long id);

}
