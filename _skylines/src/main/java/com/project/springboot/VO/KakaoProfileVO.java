package com.project.springboot.VO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;



@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoProfileVO {

public String id;
public String connectedAt;
public Kakao_Account Kakao_account;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Kakao_Account{
	public String email;
	/*
	 * public Boolean hasEmail; 
	 * public Boolean emailNeedsAgreement; 
	 * public Boolean isEmailValid; 
	 * public Boolean isEmailVerified; 
	 * public Boolean hasAgeRange;
	 * public Boolean ageRangeNeedsAgreement; 
	 * public Boolean hasBirthday; 
	 * public Boolean birthdayNeedsAgreement;
	 */
};

}