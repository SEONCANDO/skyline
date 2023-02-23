package com.project.springboot.VO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReservationVO {
	
	private int reservationNum;
	private String userId;
	private int flightScheduleNum;
	private String fromPlatform;
	private String toPlatform;
	private int agedPerson;
	private int totalPrice;
	private String flightTicket;
	

	
}
