package com.kafein.rental.service;


import com.kafein.rental.common.ServiceResponse;
import com.kafein.rental.model.Company;

public interface CompanyService {

	public ServiceResponse save(Company entity);
	
	public ServiceResponse update(Company entity);
	
	public ServiceResponse getById(Long id);
	
	public ServiceResponse findAll();
	
	public ServiceResponse deleteById(Long id);
	
}
