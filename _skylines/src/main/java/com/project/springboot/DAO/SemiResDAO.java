package com.project.springboot.DAO;

import org.apache.ibatis.annotations.Mapper;

import com.project.springboot.VO.SemiResVO;

@Mapper
public interface SemiResDAO {
	int insertres(SemiResVO srVO);	
}
