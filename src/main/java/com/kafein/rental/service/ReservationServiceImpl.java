package com.kafein.rental.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kafein.rental.common.ServiceResponse;
import com.kafein.rental.model.Company;
import com.kafein.rental.model.MeetingRoom;
import com.kafein.rental.model.Reservation;
import com.kafein.rental.repository.CompanyRepository;
import com.kafein.rental.repository.MeetingRoomRepository;
import com.kafein.rental.repository.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	MeetingRoomRepository meetingRoomRepository;
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Override
	public ServiceResponse save(Reservation entity) {
	
		if(entity.getId() != null) {
			return new ServiceResponse(HttpStatus.BAD_REQUEST, "Id must be null.", null);
		}
		
		ServiceResponse isValid = validateRequest(entity);
		if(isValid != null) {
			return isValid;
		}
		
		Reservation saved = reservationRepository.save(entity);
		if(saved != null) {
			return new ServiceResponse(HttpStatus.OK, "The meeting room has been reserved successfully", saved);
		}else {
			return new ServiceResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Error", null);
		}	
	}
	
	@Override
	public ServiceResponse update(Reservation entity) {
	
		ServiceResponse isValid = validateRequest(entity);
		if(isValid != null) {
			return isValid;
		}
		
		Optional<Reservation> result = reservationRepository.findById(entity.getId());
		if(result.isPresent()) {
			Reservation updated = reservationRepository.save(entity);
			if(updated != null) {
				return new ServiceResponse(HttpStatus.OK, "Success", updated);
			}else {
				return new ServiceResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Error", null);
			}
		}else {
			return new ServiceResponse(HttpStatus.BAD_REQUEST, "Not found", null);
		}
	}
	
	public ServiceResponse validateRequest(Reservation entity) {
		
		if(entity.getStartDate() == null || entity.getEndDate() == null || entity.getEndDate().before(entity.getStartDate())) {
			return new ServiceResponse(HttpStatus.BAD_REQUEST, "Invalid date", null);
		}
		
		

		Optional<Company> company = companyRepository.findById(entity.getCompany().getId());
		if(!company.isPresent()) {
			return new ServiceResponse(HttpStatus.BAD_REQUEST, "Company not found.", null);
		}
		
		
		Optional<MeetingRoom> meetingRoom = meetingRoomRepository.findById(entity.getMeetingRoom().getId());
		if(!meetingRoom.isPresent()) {
			return new ServiceResponse(HttpStatus.BAD_REQUEST, "Meeting Room not found.", null);
		}
		
		List<Reservation> currentReservations4Company = reservationRepository.findCompanyReservationInDateRange(entity.getCompany().getId(),entity.getMeetingRoom().getId(), entity.getStartDate(), entity.getEndDate());
		if(currentReservations4Company != null && currentReservations4Company.size() > 0) {
			return new ServiceResponse(HttpStatus.BAD_REQUEST, "Company allready reserved this meeting room", null);
		}
		
		List<Reservation> currentReservations4MeetingRoom = reservationRepository.findMeetingRoomReservationInDateRange(entity.getMeetingRoom().getId(), entity.getStartDate(), entity.getEndDate());
		if(currentReservations4MeetingRoom != null && currentReservations4MeetingRoom.size() > 0) {
			return new ServiceResponse(HttpStatus.BAD_REQUEST, "Meeting room has been reserved. Please try for other times.", null);
		}
		
		
		if(entity.getCapacity() > meetingRoom.get().getCapacity()) {
			
			String message = "This meeting room’s capacit  restricted " + meetingRoom.get().getCapacity() + " people";
			return new ServiceResponse(HttpStatus.BAD_REQUEST, message, null);
		}
		
		return null;
		
	}

	@Override
	public ServiceResponse getById(Long id) {
	
		if(id == null) {
			return new ServiceResponse(HttpStatus.BAD_REQUEST, "Invalid ID", null);
		}
		
		Optional<Reservation> result = reservationRepository.findById(id);
		if(result.isPresent()) {
			return new ServiceResponse(HttpStatus.OK, "Success", result.get());
		}else {
			return new ServiceResponse(HttpStatus.BAD_REQUEST, "Not found", null);
		}
	}

	@Override
	public ServiceResponse findAll() {
		
		List<Reservation> results = reservationRepository.findAll();
		if(results.size() > 0) {
			return new ServiceResponse(HttpStatus.OK, "Success", results);
		}else {
			return new ServiceResponse(HttpStatus.BAD_REQUEST, "Not found", null);
		}	
	}

	@Override
	public ServiceResponse deleteById(Long id) {

		Optional<Reservation> result = reservationRepository.findById(id);
		if(result.isPresent()) {
			reservationRepository.deleteById(id);
			return new ServiceResponse(HttpStatus.OK, "Success", null);
		}else {
			return new ServiceResponse(HttpStatus.BAD_REQUEST, "Not found", null);
		}
	}

}
