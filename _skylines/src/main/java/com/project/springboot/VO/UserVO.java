package com.project.springboot.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserVO {
	String userId;
	String userPw;
	String userEmail;
	String userFirstName;
	String userLastName;
	String signLocation;
}