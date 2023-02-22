package com.project.springboot.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.springboot.VO.BbsVO;

@Mapper
public interface BbsDAO {
	List<BbsVO> selectBbs();
	List<BbsVO> selectmain1();
	List<BbsVO> selectmain2();
	int write2(BbsVO bbsVO);
	int update(BbsVO bbsVO);
	BbsVO selectOne(int bbsId);
	int delete(int bbsId);

}
