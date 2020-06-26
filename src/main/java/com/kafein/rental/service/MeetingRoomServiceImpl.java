package com.kafein.rental.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.kafein.rental.common.ServiceResponse;
import com.kafein.rental.model.MeetingRoom;
import com.kafein.rental.repository.MeetingRoomRepository;

@Service
public class MeetingRoomServiceImpl implements MeetingRoomService {

	@Autowired
	MeetingRoomRepository meetingRoomRepository;

	@Override
	public ServiceResponse save(MeetingRoom entity) {
	
		if(entity.getId() != null) {
			return new ServiceResponse(HttpStatus.BAD_REQUEST, "Invalid data", null);
		}
		
		MeetingRoom saved = meetingRoomRepository.save(entity);
		if(saved != null) {
			return new ServiceResponse(HttpStatus.OK, "Success", saved);
		}else {
			return new ServiceResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Error", null);
		}
	}
	
	@Override
	public ServiceResponse update(MeetingRoom entity) {
	
		Optional<MeetingRoom> result = meetingRoomRepository.findById(entity.getId());
		if(result.isPresent()) {
			MeetingRoom updated = meetingRoomRepository.save(entity);
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
		
		Optional<MeetingRoom> result = meetingRoomRepository.findById(id);
		if(result.isPresent()) {
			return new ServiceResponse(HttpStatus.OK, "Success", result.get());
		}else {
			return new ServiceResponse(HttpStatus.BAD_REQUEST, "Not found", null);
		}
	}

	@Override
	public ServiceResponse findAll() {

		List<MeetingRoom> results = meetingRoomRepository.findAll();
		if(results.size() > 0) {
			return new ServiceResponse(HttpStatus.OK, "Success", results);
		}else {
			return new ServiceResponse(HttpStatus.BAD_REQUEST, "Not found", null);
		}	
	}

	@Override
	public ServiceResponse deleteById(Long id) {

		Optional<MeetingRoom> result = meetingRoomRepository.findById(id);
		if(result.isPresent()) {
			meetingRoomRepository.deleteById(id);
			return new ServiceResponse(HttpStatus.OK, "Success", null);
		}else {
			return new ServiceResponse(HttpStatus.BAD_REQUEST, "Not found", null);
		}
	}
}
