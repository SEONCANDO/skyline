package com.project.springboot.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.project.springboot.VO.FlightScheduleVO;

@Mapper
public interface FlightScheduleMapper {
	@Select("select * from flightschedule")
	List<FlightScheduleVO> selectAll();
}
