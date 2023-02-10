package com.project.springboot.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.springboot.DAO.FlightScheduleDAO;
import com.project.springboot.DAO.PlatformDAO;
import com.project.springboot.DAO.ReservationDAO;
import com.project.springboot.DAO.UserDAO;
import com.project.springboot.Service.UserService;
import com.project.springboot.Service.UserServiceImp;
import com.project.springboot.VO.KakaoProfileVO;
import com.project.springboot.VO.OAuthTokenVO;
import com.project.springboot.VO.PlatformVO;
import com.project.springboot.VO.UserVO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MyController {
	
	@Autowired
	UserServiceImp userService;
	
	PlatformDAO pfMapper;
	FlightScheduleDAO fsMapper;
	ReservationDAO rsMapper;
	UserDAO userMapper;
	
	@Autowired
	public MyController(PlatformDAO pfMapper, FlightScheduleDAO fsMapper, ReservationDAO rsMapper, UserDAO userMapper) {
		super();
		this.pfMapper = pfMapper;
		this.fsMapper = fsMapper;
		this.rsMapper = rsMapper;
		this.userMapper = userMapper;
	}
	
	


	@GetMapping("/test")
	public String list(Model model) {
		List<PlatformVO> plst = pfMapper.selectName("i");
		model.addAttribute("list",plst);
		return "testlist";
	}
	
	

	@GetMapping("/test2")
	public void list2() {
	}

	@GetMapping("/login")
	public void login() {
	}
	
	@GetMapping("/home")
	public void home() {
	}
	
	@GetMapping("/auth/kakao/callback")
	public String  callback(String code, Model mo,HttpSession session) {
		
		UserVO user = userService.kakaoUser(code);
		
		System.out.println("항공예매사이트 유저Id>>"+user.getUserId());
		System.out.println("항공예매사이트 유저Email>>"+user.getUserEmail());
		
		UserVO userId_DB = userMapper.selectOneId(user.getUserId());
		if(userId_DB != null) {
			System.out.println(user);
			if(userId_DB.getUserFirstName() != null) {
				mo.addAttribute("alert",userId_DB.getUserFirstName()+"님 반갑습니다.");
			}else{
				mo.addAttribute("alert",userId_DB.getUserId()+"님 반갑습니다.");
			}
			session.setAttribute("userId", userId_DB.getUserId());
			return "/alert";
		}else {
			System.out.println(user);
			int i = userMapper.simpleSign(user);
			mo.addAttribute("alert","회원가입이 완료되었습니다.");
			session.setAttribute("userId", user);
			return "/alert";
		}
		
	}
	
}
