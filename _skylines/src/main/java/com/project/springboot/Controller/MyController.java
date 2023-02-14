package com.project.springboot.Controller;

import java.util.List;

import javax.tools.DocumentationTool.Location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
import com.project.springboot.VO.NaverprofileVO;
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
	
	@PostMapping("/login")
	public String callbackSkyline(UserVO user,Model mo, HttpSession session) {
		UserVO userId_DB = userMapper.authentication(user);
		
		if(userId_DB != null) {
			System.out.println(user);
			if(userId_DB.getUserFirstName() != null) {
				mo.addAttribute("alert",userId_DB.getUserFirstName()+"님 반갑습니다.");
			}else{
				mo.addAttribute("alert",userId_DB.getUserId()+"님 반갑습니다.");
			}
			session.setAttribute("user", userId_DB);
			mo.addAttribute("url","/home");
			return "/alert";
		}else {
			System.out.println(user);
			mo.addAttribute("alert","아이디와 비밀번호를 확인해주세요.");
			mo.addAttribute("url","/login");
			session.setAttribute("user", null);
			String sessionUser = (String)session.getAttribute("user");
			mo.addAttribute("sessionUser", sessionUser);
			return "/alert";
		}
	}
	
	@GetMapping("/logout")
	public String logout(Model mo,HttpSession sess) {
		mo.addAttribute("alert","로그아웃되었습니다.");
		mo.addAttribute("url","/home");
		sess.setAttribute("user", null);
		return "/alert";
	}
	
	@GetMapping("/home")
	public void home() {
	}
	
	@GetMapping("/auth/callback/{type}")
	public String  callbackKakao(String code,@PathVariable String type, Model mo,HttpSession session) {
		
		UserVO user = null;
		if(type.equals("kakao")) {
			user = userService.kakaoUser(code);
		}else if(type.equals("naver")) {
			user = userService.naverUser(code);
		}else {
			mo.addAttribute("alert","로그인의 실패하였습니다.");
			mo.addAttribute("url","/home");
			return "/alert";
		}
		
		/*
		 * System.out.println("항공예매사이트 유저Id>>"+user.getUserId());
		 * System.out.println("항공예매사이트 유저Email>>"+user.getUserEmail());
		 */
		UserVO userId_DB = userMapper.selectOneId(user.getUserId());
		if(userId_DB != null) {
			System.out.println(user);
			if(userId_DB.getUserFirstName() != null) {
				mo.addAttribute("alert",userId_DB.getUserFirstName()+"님 반갑습니다.");
			}else{
				mo.addAttribute("alert",userId_DB.getUserId()+"님 반갑습니다.");
			}
			mo.addAttribute("url","/home");
			session.setAttribute("user", userId_DB);
			return "/alert";
		}else {
			System.out.println(user);
			int i = userMapper.simpleSign(user);
			mo.addAttribute("alert","회원가입이 완료되었습니다.");
			mo.addAttribute("url","/home");
			session.setAttribute("user", user);
			return "/alert";
		}
		
	}
	
		  
	  
	  @GetMapping("/auth/naver/callback") 
	  public @ResponseBody UserVO callbackNaver(String code) {
		  
		  UserVO user = userService.naverUser(code);
		  
		  return user;
		  }
}
