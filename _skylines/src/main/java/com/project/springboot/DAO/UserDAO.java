package com.project.springboot.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.springboot.VO.UserVO;

@Mapper
public interface UserDAO {
	List<UserVO> selectAll();
	UserVO inspectionId(String userId);
	UserVO authentication(UserVO user);
	int simpleSign(UserVO user);
	int fullSign(UserVO user);
}