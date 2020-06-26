package com.kafein.rental.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kafein.rental.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{
	
	@Query(value = "SELECT r FROM Reservation r WHERE r.company.id = ?1 and r.meetingRoom.id = ?2 and r.startDate < ?4 and r.endDate > ?3 ")
	List<Reservation> findCompanyReservationInDateRange(Long companyId,Long meetingRoomId, Date startDate, Date endDate);
	
	@Query(value = "SELECT r FROM Reservation r WHERE r.meetingRoom.id = ?1 and r.startDate < ?3 and r.endDate > ?2 ")
	List<Reservation> findMeetingRoomReservationInDateRange( Long meetingRoomId, Date startDate, Date endDate);
	
}
