package com.studycafe.scafe.reservation.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studycafe.scafe.reservation.domain.RoomReservation;
import com.studycafe.scafe.reservation.repository.RoomReservationRepository;

@Service
public class RoomReservationServiceImpl implements RoomReservationService {

	@Autowired
	RoomReservationRepository roomReservationRepository;
	
	//예약하기
	public int insertRoomReservation(RoomReservation roomReservation) throws Exception{
		return roomReservationRepository.insertRoomReservation(roomReservation);
	}
	
	//예약 전체조회
	public List<RoomReservation> getAllReservationDetail() throws Exception{
		return roomReservationRepository.getAllReservationDetail();
	}
	
	//예약 상세조회
	public List<RoomReservation> getReservationDetail(int user_number) throws Exception{
		return roomReservationRepository.getReservationDetail(user_number);
	}
	
	//날짜별 예약가능 조회
	public RoomReservation timeCheckA(String rs_date) throws Exception{
		return roomReservationRepository.timeCheckA(rs_date);
	}
	
}
