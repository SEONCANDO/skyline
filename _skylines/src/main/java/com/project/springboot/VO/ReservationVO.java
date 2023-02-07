package com.project.springboot.VO;

import lombok.Data;

@Data
public class ReservationVO {
	int reservationNum,flightScheduleNum,fromPlatformNum,toPlatformNum;
	String userId;
}
