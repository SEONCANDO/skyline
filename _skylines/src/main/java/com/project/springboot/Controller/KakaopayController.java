package com.project.springboot.Controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.module.ModuleDescriptor.Modifier;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/a")
public class KakaopayController {
	
	@GetMapping("/kakaopay")
	@ResponseBody
	public String kakaopay(){
		
		try {
			URL location = new URL("http://kapi.kakao.com/v1/payment/ready");
			HttpURLConnection connection = (HttpURLConnection) location.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Authorization", "KakaoAK 6ce5d068dcbd34d03d2ac455428d94af");
			connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			connection.setDoOutput(true);
			String param1 = "cid=TC0ONETIME&partner_order_id=partner_order_id&partner_user_id=partner_user_id&item_name=항공예약&quantity=1&total_amount=0&vat_amount=0&tax_free_amount=0&approval_url=http://localhost:7075&fail_url=http://localhost:7075&cancel_url=http://localhost:7075";
			OutputStream provider = connection.getOutputStream();
			DataOutputStream dataprovider = new DataOutputStream(provider);
			dataprovider.writeBytes(param1);
			dataprovider.close();
			
			int res = connection.getResponseCode();
			System.out.println(res);
			InputStream supplier;
			if(res == 200) {
				supplier = connection.getInputStream();
			} else {
				supplier = connection.getErrorStream();
			}
			
			InputStreamReader reader = new InputStreamReader(supplier);
			BufferedReader changes = new BufferedReader(reader);
			return changes.readLine();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "{\'result\':\'NO\'}";
	}
	
	
}
