package com.project.springboot.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.springboot.VO.ReservationVO;

@Mapper
public interface ReservationDAO {
	List<ReservationVO> selectAll();
	int save (ReservationVO rs_vo);
	int delete(int reservationNum);
}
