package com.project.springboot.VO;

import lombok.Data;

@Data
public class FlightScheduleVO {
	int flightscheduleNum;
	String fromPlatform,toPlatform,date;
}
