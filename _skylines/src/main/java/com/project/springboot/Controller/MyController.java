package com.project.springboot.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.springboot.DAO.BbsDAO;
import com.project.springboot.DAO.FlightScheduleDAO;
import com.project.springboot.DAO.PlatformDAO;
import com.project.springboot.DAO.ReservationDAO;
import com.project.springboot.DAO.UserDAO;
import com.project.springboot.Service.UserServiceImp;
import com.project.springboot.VO.BbsVO;
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
	BbsDAO bbsMapper;
	
	@Autowired
	public MyController(PlatformDAO pfMapper, FlightScheduleDAO fsMapper, ReservationDAO rsMapper, UserDAO userMapper,BbsDAO bbsMapper) {
		super();
		this.pfMapper = pfMapper;
		this.fsMapper = fsMapper;
		this.rsMapper = rsMapper;
		this.userMapper = userMapper;
		this.bbsMapper = bbsMapper;
	}
	
	@GetMapping("/")
	public String index() {
		return "/home";
	}

	@GetMapping("/login")
	public void login() {
	}
	
	
	@PostMapping("/login")
	public String callbackSkyline(UserVO user,Model mo, HttpSession session) {
		UserVO userId_DB = userMapper.authentication(user);
		
		if(userId_DB != null) {
			System.out.println(userId_DB);
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
	
	@GetMapping("/signup")
	public void signup() {
	}
	
	@PostMapping("/inspection")
	public @ResponseBody boolean inspectionId(@RequestParam("userId") String userId) {
		System.out.println(userId);
		UserVO user_db = userMapper.inspectionId(userId);
		
		if(user_db == null) {
			return true;
		}else {
			return false;
		}
	}
	
	@PostMapping("/signup")
	public String signupSkyline(UserVO user,Model mo,HttpSession session) {
		
		int i = userMapper.fullSign(user);
		mo.addAttribute("alert","회원가입이 완료되었습니다.");
		mo.addAttribute("url","/home");
		session.setAttribute("user", user);
		return "/alert";
	}
	
	
	@GetMapping("/home")
	public void home() {
	}
	
	@GetMapping("/mypage")
	public String mypage(HttpSession session) {
		String userSession = String.valueOf(session.getAttribute("user"));
		if(userSession != null) {
			System.out.println(userSession);
			return "/mypage";
		}else {
			return "/login";
		}
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
		UserVO userId_DB = userMapper.inspectionId(user.getUserId());
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
	
	@GetMapping("/userInformationEdit")
	public String userEdit(HttpSession session, Model mo) {
		String userSession = String.valueOf(session.getAttribute("user"));
		if(userSession != null) {
			System.out.println(userSession);
			return "/userInformationEdit";
		}else {
			mo.addAttribute("alert", "로그인 하세요");
			mo.addAttribute("url", "/login");
			return "/alert";
			
		}
	}
	
	@PostMapping("/userInformationEdit")
	public String userEdit2(UserVO user, Model mo, HttpSession session) {
		if(user.getUserPw().equals("")||user.getUserPw()==null) {
			mo.addAttribute("alert","비밀번호를 정확하게 입력해주세요.");
			mo.addAttribute("url","/userInformationEdit");
			return "/alert";
		}else {
			int i = userMapper.userInformationEdit(user);
			mo.addAttribute("alert","회원정보수정이 완료되었습니다.");
			mo.addAttribute("url","/home");
			session.setAttribute("user", user);
			return "/alert";
		}
		}
	
	@GetMapping("/bbs")
	public void bbs(BbsVO bbsVO, Model model, HttpSession session) {
		List<BbsVO> list = bbsMapper.selectBbs();
		model.addAttribute("list", list);
		session.getAttribute("user");
		System.out.println(list);
	}		
	
	
	
	@GetMapping("/view")
	public String view(BbsVO bbsVO, Model model, HttpSession session) {
		int bbsId = bbsVO.getBbsId();
		BbsVO list2 = bbsMapper.selectOne(bbsId);
		model.addAttribute("list2", list2);
		session.getAttribute("user");
		return "view";
	}
	
	
	
	
	@GetMapping("/write")
	public void write() {
		
	}
	@PostMapping("/write")
	public String write2(BbsVO bbsVO ,Model model, HttpSession session) {
		bbsMapper.write2(bbsVO);
		session.getAttribute("user");
		
		return "/bbs";
		
	}
	
	@GetMapping("/update")
	public void update(BbsVO bbsVO, Model model, HttpSession session) {
		int bbsId = bbsVO.getBbsId();
		BbsVO list3 = bbsMapper.selectOne(bbsId);
		System.out.println(bbsId);
		model.addAttribute("list3", list3);
		session.getAttribute("user");
	}
	
	@PostMapping("/update")
	public String update2(BbsVO bbsVO ,Model model, HttpSession session) {
		bbsMapper.update(bbsVO);
		
		int bbsId = bbsVO.getBbsId();
		BbsVO list2 = bbsMapper.selectOne(bbsId);
		System.out.println(list2);
		model.addAttribute("list2", list2);
		session.getAttribute("user");
		return "view";
		
	}
	
	@GetMapping("/myPage2")
	public String myPage2(HttpSession session, Model mo) {
		String userSession = String.valueOf(session.getAttribute("user"));
		UserVO user = (UserVO) session.getAttribute("user");
		if(userSession != null) {
		 mo.addAttribute("user",  user);
			System.out.println(user.getUserId() + "허허");
		    	
			return "/mypage2";
		}else {
			mo.addAttribute("alert", "로그인 하세요");
			mo.addAttribute("url", "/login");
			return "/alert";
		}
		
		
	}
	
	
	@GetMapping("/booking")
	public String booking(HttpSession session, Model mo) {
	   session.getAttribute("user");
	   String userSession = String.valueOf(session.getAttribute("user"));
		if(userSession != "null") {
			System.out.println(userSession + "허허");
			return "/booking";
		}else {
			mo.addAttribute("alert", "로그인 하세요");
			mo.addAttribute("url", "/login");
			return "/alert";
		}
	}
	
	@GetMapping("/booking2")
	public void booking2(HttpSession session) {
		session.getAttribute("user");
		
	}
	
	@GetMapping("/booking3")
	public void booking3(HttpSession session) {
		session.getAttribute("user");
	}
	
	@GetMapping("/delete")
	public String delete(BbsVO bbsVO, HttpSession session) {
		int bbsId = bbsVO.getBbsId();
		bbsMapper.delete(bbsId);
		session.getAttribute("user");
		return "/bbs";
	}
}
