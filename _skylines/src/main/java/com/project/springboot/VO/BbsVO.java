package com.project.springboot.VO;

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
	private String writeTime;
	private String bbsHead;
	private String bbsContent;
}
