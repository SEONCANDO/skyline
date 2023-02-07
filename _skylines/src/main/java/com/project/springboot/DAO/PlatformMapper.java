package com.project.springboot.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.project.springboot.VO.PlatformVO;

@Mapper
public interface PlatformMapper {
	@Select("select * from platform")
	List<PlatformVO> selectAll();
	
	@Select("select * from platform where platformNum like concat('%',#{platformNum},'%')")
	List<PlatformVO> selectNum(int platformNum);
	
	@Select("select * from platform where platformName like concat('%',#{platform},'%') or platformCode like concat('%',#{platform},'%')")
	List<PlatformVO> selectName(String platform);
}
