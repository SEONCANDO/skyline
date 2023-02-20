package com.project.springboot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.springboot.DAO.BbsDAO;
import com.project.springboot.DAO.FlightScheduleDAO;
import com.project.springboot.DAO.PlatformDAO;
import com.project.springboot.DAO.ReservationDAO;
import com.project.springboot.DAO.UserDAO;
import com.project.springboot.Service.UserServiceImp;
import com.project.springboot.VO.ReservationVO;
import com.project.springboot.VO.UserVO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
public class ReservationController {

	@Autowired
	UserServiceImp userService;
	
	PlatformDAO pfMapper;
	FlightScheduleDAO fsMapper;
	ReservationDAO rsMapper;
	UserDAO userMapper;
	BbsDAO bbsMapper;
	
	@Autowired
	public ReservationController(PlatformDAO pfMapper, FlightScheduleDAO fsMapper, ReservationDAO rsMapper, UserDAO userMapper,BbsDAO bbsMapper) {
		super();
		this.pfMapper = pfMapper;
		this.fsMapper = fsMapper;
		this.rsMapper = rsMapper;
		this.userMapper = userMapper;
		this.bbsMapper = bbsMapper;
	}
	
	


}
