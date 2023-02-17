package com.project.springboot.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.springboot.VO.BbsVO;

@Mapper
public interface BbsDao {
	List<BbsVO> selectBbs();
	int write(BbsVO bbs);
	int update(BbsVO bbs);
	
}
