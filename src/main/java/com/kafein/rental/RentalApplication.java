package com.kafein.rental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kafein.rental.repository.CompanyRepository;

@SpringBootApplication
public class RentalApplication {

	@Autowired
	CompanyRepository companyRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(RentalApplication.class, args);
	}

}
