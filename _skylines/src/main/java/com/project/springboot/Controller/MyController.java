package com.project.springboot.Controller;

import java.security.Provider.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.springboot.DAO.FlightScheduleMapper;
import com.project.springboot.DAO.PlatformMapper;
import com.project.springboot.DAO.ReservationMapper;
import com.project.springboot.VO.PlatformVO;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class MyController {
	
	
	PlatformMapper pfMapper;
	FlightScheduleMapper fsMapper;
	ReservationMapper rsMapper;
	
	@Autowired
	public MyController(PlatformMapper pfMapper, FlightScheduleMapper fsMapper,ReservationMapper rsMapper) {
		super();
		this.pfMapper = pfMapper;
		this.fsMapper = fsMapper;
		this.rsMapper = rsMapper;
	}
	
	



	@GetMapping("/test")
	public String list(Model model) {
		List<PlatformVO> plst = pfMapper.selectName("i");
		model.addAttribute("list",plst);
		return "testlist";
	}
	

	@GetMapping("/test2")
	public String tt(@RequestParam("code") String code) {
		log.info("code::::"+code);
		return "test2";
	}
}
