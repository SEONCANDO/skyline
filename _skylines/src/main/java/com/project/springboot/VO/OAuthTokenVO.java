package com.project.springboot.VO;

import lombok.Data;

@Data

public class OAuthTokenVO {
	private String access_token;
	private String token_type;
	private String refresh_token;
	private int expires_in;
	private String scope;
	private int refresh_token_expires_in;
}