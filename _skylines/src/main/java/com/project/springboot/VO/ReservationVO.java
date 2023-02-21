package com.project.springboot.VO;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ReservationVO {
	
	private int reservationNum;
	private String userId;
	private int flightScheduleNum;
	private int fromPlatformNum;
	private int toPlatformNum;
	private int agedPerson;
	private int youngPerson;
	private int totalPrice;
	private String flightTicket;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private DateTimeFormat toTime;
	private DateTimeFormat fromTime;
	

	
}
