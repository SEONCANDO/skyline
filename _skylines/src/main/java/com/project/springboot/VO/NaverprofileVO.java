package com.project.springboot.VO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NaverprofileVO {

	public String resultcode;
	public String message;
	public Response response;

	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public class Response {

		public String id;
		public String gender;
		public String email;
		public String mobile;
		public String mobileE164;
		public String name;
		public String birthday;
	}
}