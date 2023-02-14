package com.project.springboot.Service;

import com.project.springboot.VO.UserVO;

public interface UserService {
	
	public UserVO kakaoUser(String code);
	public UserVO naverUser(String code);
}
