package com.kafein.rental.service;

import com.kafein.rental.common.ServiceResponse;
import com.kafein.rental.model.Reservation;

public interface ReservationService {

	public ServiceResponse save(Reservation entity);
	
	public ServiceResponse update(Reservation entity);
	
	public ServiceResponse getById(Long id);
	
	public ServiceResponse findAll();
	
	public ServiceResponse deleteById(Long id);
		
}
