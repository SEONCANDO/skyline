package com.project.springboot.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.springboot.DAO.ReservationDAO;
import com.project.springboot.VO.ReservationVO;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class ReservationController {

	@Autowired
	ReservationDAO rsMapper;

	@Autowired
	public ReservationController(ReservationDAO rsMapper) {
		super();

		this.rsMapper = rsMapper;

	}
	
	@GetMapping("/rs_list")
	public String list(Model model) {
		List<ReservationVO> rs_vo = rsMapper.findAll();
		model.addAttribute("rs_list",rs_vo);
		return "rs_list";
	}
	
	@PostMapping("/rs_insert")
	public String insert(ReservationVO rs_vo,Model model) {
		//System.out.println(rs_vo);
		int res = rsMapper.save(rs_vo);
		//model.addAttribute("res",res);
		//return "result";
		return"redirect:/rs_list";
		// redirect는 값을 못 넘김
	}
	
	@GetMapping("/rs_delete")
	public String delete(int reservationNum) {
		rsMapper.delete(reservationNum);
		return "redirect:/rs_list";
	}
	
}
	
