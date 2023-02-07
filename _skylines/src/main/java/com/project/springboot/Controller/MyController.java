package com.project.springboot.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.springboot.DAO.FlightScheduleMapper;
import com.project.springboot.DAO.PlatformMapper;
import com.project.springboot.DAO.ReservationMapper;
import com.project.springboot.VO.PlatformVO;

@Controller
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
}
