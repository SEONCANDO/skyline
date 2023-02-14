package com.project.springboot.Service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.springboot.VO.KakaoProfileVO;
import com.project.springboot.VO.NaverprofileVO;
import com.project.springboot.VO.OAuthTokenVO;
import com.project.springboot.VO.UserVO;

@Service
public class UserServiceImp implements UserService {

	@Override
	public UserVO kakaoUser(String code) {
		/* HttpsURLConnection을 사용하여 보낼 수 있으나 불편하여 RestTemplate사용 Retrofit2는 안드로이드에서 주로
		 * 사용 이외에 Okhttp등이 있음
		 */
		RestTemplate rt = new RestTemplate();
		
		/*HttpHeader 오브젝트 생성*/
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		/* HttpBody 오브젝트 생성 */
		MultiValueMap<String , String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "0f40b989fc6cfe4ce8e373beaa38778d");
		params.add("redirect_uri", "http://localhost:7075/auth/callback/kakao");
		params.add("code", code);
		params.add("client_secret", "Envu7oFxDfK6eBApOvGmSCuF70OynQt7");
		
		/* HttpHeader와 Body를 하나의 오브젝트에 담기 */
		HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest = new HttpEntity<>(params,headers);
		
		/* exchange라는 함수가 HttpEntity로 오브젝트를 받기 때문에 하나의 오브젝트에 담음 */
		/* Http 요청하기 - 요청주소, post방식, response변수의 응답 */
		ResponseEntity<String> response = rt.exchange(
				"https://kauth.kakao.com/oauth/token",
				HttpMethod.POST,
				kakaoTokenRequest,
				String.class
				);
		
		/* response 데이터를 오브젝트에 담기위하여 gson,json, objectMapper를 사용할 수 있음 */
		ObjectMapper obMapper = new ObjectMapper();
		OAuthTokenVO oauthToken = null;
		try {
			oauthToken = obMapper.readValue(response.getBody(), OAuthTokenVO.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		/* 토큰을 이용한 사용자 정보 보기 */
		RestTemplate rt2 = new RestTemplate();
		
		/*HttpHeader 오브젝트 생성*/
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer "+oauthToken.getAccess_token());
		
		HttpEntity<MultiValueMap<String,String>> kakaoProfileRequest = new HttpEntity<>(headers2);
		
		/* exchange라는 함수가 HttpEntity로 오브젝트를 받기 때문에 하나의 오브젝트에 담음 */
		/* Http 요청하기 - 요청주소, get방식, response변수의 응답 */
		ResponseEntity<String> response2 = rt2.exchange(
				"https://kapi.kakao.com/v2/user/me",
				HttpMethod.POST,
				kakaoProfileRequest,
				String.class
				);
		
		System.out.println(response2.getBody());
		
		ObjectMapper obMapper2 = new ObjectMapper();
		KakaoProfileVO kakaoProfile = null;
		try {
			kakaoProfile = obMapper2.readValue(response2.getBody(), KakaoProfileVO.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//카카오 유저 정보
		/*
		 * System.out.println("카카오ID_seq>>"+ kakaoProfile.getId());
		 * System.out.println("카카오email>>"+ kakaoProfile.getKakao_account().getEmail());
		 */
		String kuserId = kakaoProfile.getKakao_account().getEmail() +"/"+ kakaoProfile.getId();
		String kuserEmail = kakaoProfile.getKakao_account().getEmail();
		
		
		UserVO user = UserVO.builder().userId(kuserId).userEmail(kuserEmail).signLocation("kakao").build();
		
		return user;
	}

	@Override
	public UserVO naverUser(String code) {

		  HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	        
		  MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			params.set("grant_type", "authorization_code");
			params.set("client_id", "OyuBZVnfbn0mOSkZl4lQ");
			params.set("client_secret", "unb1SMMQNT");
			params.set("code", code);
			params.set("state", "state_string");
			
			HttpEntity<MultiValueMap<String, String>> naverTokenRequest = new HttpEntity<>(params,headers);
			
			RestTemplate rt = new RestTemplate();
			ResponseEntity<String> response = rt.postForEntity("https://nid.naver.com/oauth2.0/token",
					naverTokenRequest, String.class);
					
		  ObjectMapper obmapper = new ObjectMapper();
		  OAuthTokenVO oAuthToken = null;
		  
		  try {
			oAuthToken = obmapper.readValue(response.getBody(), OAuthTokenVO.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		  HttpHeaders headers2 = new HttpHeaders();
		  headers2.set("Authorization", "Bearer "+oAuthToken.getAccess_token());
		  System.out.println(oAuthToken.getAccess_token());
		  
		  HttpEntity<MultiValueMap<String, String>> naverprofileRequest = new HttpEntity<>(headers2);
			
			RestTemplate rt2 = new RestTemplate();
			ResponseEntity<String> response2 = rt2.postForEntity("https://openapi.naver.com/v1/nid/me",
					naverprofileRequest, String.class);
		  
			 ObjectMapper obmapper2 = new ObjectMapper();
			 NaverprofileVO naverprofile = null;
			  
			 try {
				 naverprofile = obmapper.readValue(response2.getBody(), NaverprofileVO.class);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 String NuserId = naverprofile.getResponse().getId();
			 String NuserEmail = naverprofile.getResponse().getEmail();
				/*
				 * System.out.println("네이버ID>"+NuserId);
				 * System.out.println("네이버Email>"+NuserEmail);
				 */
			 
			 UserVO user = UserVO.builder().userId(NuserId).userEmail(NuserEmail).signLocation("naver").build();
			 
		  return user; 
	}

}
