package com.kafein.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kafein.rental.model.Company;

@Repository
public interface CompanyRepository  extends JpaRepository<Company, Long> {

	
	
}
