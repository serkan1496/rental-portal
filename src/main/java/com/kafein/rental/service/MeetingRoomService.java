package com.kafein.rental.service;

import com.kafein.rental.common.ServiceResponse;
import com.kafein.rental.model.MeetingRoom;

public interface MeetingRoomService {

	public ServiceResponse save(MeetingRoom entity);
	
	public ServiceResponse update(MeetingRoom entity);
	
	public ServiceResponse getById(Long id);
	
	public ServiceResponse findAll();
	
	public ServiceResponse deleteById(Long id);
	
}
