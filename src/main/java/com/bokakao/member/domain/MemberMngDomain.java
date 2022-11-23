package com.bokakao.member.domain;

import lombok.Data;

@Data
public class MemberMngDomain {
	
	private String uid;
	private String access_token;
	private String refresh_token;	
	private String refresh_token_expires_in;
	private String mem_nm;	
	private String mem_email;	
	private String mem_password;
	private String mem_point;
}
