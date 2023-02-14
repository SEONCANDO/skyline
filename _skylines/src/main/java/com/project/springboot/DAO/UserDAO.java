package com.project.springboot.DAO;

import org.apache.ibatis.annotations.Mapper;

import com.project.springboot.VO.UserVO;

@Mapper
public interface UserDAO {
	UserVO selectOneId(String userId);
	int simpleSign(UserVO user);
	UserVO authentication(UserVO user);
}
