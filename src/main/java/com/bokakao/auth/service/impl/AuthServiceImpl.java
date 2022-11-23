package com.bokakao.auth.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bokakao.auth.service.AuthService;
import com.bokakao.member.domain.MemberMngDomain;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@Service("AuthService")
public class AuthServiceImpl implements AuthService {

	@Value("${kakao_api_key}")
	private String KAKAO_API_KEY;
	
	@Value("${kakao_auth_url}")
	private String KAKAO_AUTH_URL;
	
	 /**
     * 카카오 로그인
     * @param 
     * @return
     * @throws Exception
     */
	@Override
	public Integer getKaKaoLogin(MemberMngDomain memberMngDomain) throws Exception {
		return null;
	}
	
    /**
     * 토큰 조회
     * @param 
     * @return
     * @throws Exception
     */
	@Override
	public String getAccessToken(String code) throws Exception {
		String access_token = "";
		String refresh_token = "";
		System.out.println("KAKAO_AUTH_URL :: " +  KAKAO_AUTH_URL);
		System.out.println("KAKAO_API_KEY :: " +  KAKAO_API_KEY);
		try {
			URL url = new URL(KAKAO_AUTH_URL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
            sb.append("&client_id=" + KAKAO_API_KEY);
            sb.append("&redirect_uri=http://localhost:8081/api/v1/auth/kakao");
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();
			
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            JsonElement element = JsonParser.parseString(result);

            access_token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_token = element.getAsJsonObject().get("refresh_token").getAsString();

            System.out.println("access_token : " + access_token);
            System.out.println("refresh_token : " + refresh_token);

            br.close();
            bw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return access_token;
	}
	
    /**
     * 회원정보 조회
     * @param 
     * @return
     * @throws Exception
     */
	@Override
	public MemberMngDomain getUserInfo(MemberMngDomain memberMngDomain) throws Exception {
		
		String reqURL = "https://kapi.kakao.com/v2/user/me";
		
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.addRequestProperty("Authorization", "Bearer " + memberMngDomain.getAccess_token());
			
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";
			
			while ((line = br.readLine())!= null) {
				result += line;
			}
			System.out.println("response body : " + result);
			
			JsonElement element = JsonParser.parseString(result);
			
			String id = element.getAsJsonObject().get("id").getAsString();
			boolean hasEmail = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("has_email").getAsBoolean();
			String email = "";
			if(hasEmail) {
				email = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
			}
			
			System.out.println("id :: "+ id);
			System.out.println("email :: "+ email);
			
			memberMngDomain.setUid(id);
			memberMngDomain.setMem_email(email);
			
			br.close();
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return memberMngDomain;
	}
	

}
