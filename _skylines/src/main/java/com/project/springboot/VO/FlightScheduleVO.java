package com.project.springboot.VO;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

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
public class FlightScheduleVO {
	private int flightscheduleNum;
	private String flightTicket;
	private String fromPlatform;
	private String toPlatform;
	private String date;
	private int limitSeat;
	private int nowSeat;
	
}
