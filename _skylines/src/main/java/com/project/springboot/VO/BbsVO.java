package com.project.springboot.VO;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BbsVO {
	
	private int bbsId;
	private String userId;
	
	private String bbsHead;
	private String bbsContent;
	
	@DateTimeFormat(pattern = "yyy-MM-dd")
	private Date writeTime;
}
