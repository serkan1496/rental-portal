package com.kafein.rental.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kafein.rental.common.ServiceResponse;
import com.kafein.rental.model.Company;
import com.kafein.rental.repository.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	CompanyRepository companyRepository;
	
	@Override
	public ServiceResponse save(Company entity) {
	
		if(entity.getId() != null) {
			return new ServiceResponse(HttpStatus.BAD_REQUEST, "Invalid data", null);
		}
		
		Company saved = companyRepository.save(entity);
		if(saved != null) {
			return new ServiceResponse(HttpStatus.OK, "Success", saved);
		}else {
			return new ServiceResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Error", null);
		}
	}
	
	@Override
	public ServiceResponse update(Company entity) {
		
		if(entity.getId() == null) {
			return new ServiceResponse(HttpStatus.BAD_REQUEST, "Id must be null.", null);
		}
		
		Optional<Company> result = companyRepository.findById(entity.getId());
		if(result.isPresent()) {
			Company updated = companyRepository.save(entity);
			if(updated != null) {
				return new ServiceResponse(HttpStatus.OK, "Success", updated);
			}else {
				return new ServiceResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Error", null);
			}
		}else {
			return new ServiceResponse(HttpStatus.BAD_REQUEST, "Not found", null);
		}
	}

	@Override
	public ServiceResponse getById(Long id) {
	
		if(id == null) {
			return new ServiceResponse(HttpStatus.BAD_REQUEST, "Invalid ID", null);
		}
		
		Optional<Company> result = companyRepository.findById(id);
		if(result.isPresent()) {
			return new ServiceResponse(HttpStatus.OK, "Success", result.get());
		}else {
			return new ServiceResponse(HttpStatus.BAD_REQUEST, "Not found", null);
		}
	}

	@Override
	public ServiceResponse findAll() {

		List<Company> results = companyRepository.findAll();
		if(results.size() > 0) {
			return new ServiceResponse(HttpStatus.OK, "Success", results);
		}else {
			return new ServiceResponse(HttpStatus.BAD_REQUEST, "Not found", null);
		}
	}

	@Override
	public ServiceResponse deleteById(Long id) {

		Optional<Company> result = companyRepository.findById(id);
		if(result.isPresent()) {
			companyRepository.deleteById(id);
			return new ServiceResponse(HttpStatus.OK, "Success", null);
		}else {
			return new ServiceResponse(HttpStatus.BAD_REQUEST, "Not found", null);
		}
	}
	
}
