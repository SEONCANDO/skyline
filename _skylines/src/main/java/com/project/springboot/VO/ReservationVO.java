package com.project.springboot.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationVO {
	private int reservationNum;
	private int flightScheduleNum;
	private int fromPlatformNum;
	private int toPlatformNum;
	private int adult;
	private int children;
	private String userId;
}
