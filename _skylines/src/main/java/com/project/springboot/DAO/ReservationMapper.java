package com.project.springboot.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.project.springboot.VO.ReservationVO;

@Mapper
public interface ReservationMapper {
	@Select("select * from reservation")
	List<ReservationVO> selectAll();
}
