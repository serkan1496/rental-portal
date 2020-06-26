package com.kafein.rental.controller;


//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafein.rental.common.ServiceResponse;
import com.kafein.rental.model.Company;
import com.kafein.rental.service.CompanyServiceImpl;

@RestController
@RequestMapping(path = "/company")
public class CompanyController {
	
//	private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);
	
	@Autowired
	CompanyServiceImpl companyService;
	
	@PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServiceResponse> save(@RequestBody Company entity) {
	
		ServiceResponse response  = companyService.save(entity);
		return new ResponseEntity<ServiceResponse> (response, response.getStatus());
	}
	
	@PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServiceResponse> update(@RequestBody Company entity) {
		
		ServiceResponse response  = companyService.update(entity);
		return new ResponseEntity<ServiceResponse> (response, response.getStatus());
	}
	
	@GetMapping(path = "/getById/{id}" ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServiceResponse> getById(@PathVariable("id") Long id){
		
		ServiceResponse response  = companyService.getById(id);
		return new ResponseEntity<ServiceResponse> (response, response.getStatus());
	}
	
	@GetMapping(path = "/findAll" ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServiceResponse> findAll(){
		
		ServiceResponse response  =  companyService.findAll();
		return new ResponseEntity<ServiceResponse> (response, response.getStatus());
	}
	
	@DeleteMapping(path = "/deleteById/{id}" ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServiceResponse> deleteById(@PathVariable("id") Long id) {
		
		ServiceResponse response = companyService.deleteById(id);
		return new ResponseEntity<ServiceResponse> (response, response.getStatus());
	}
}
