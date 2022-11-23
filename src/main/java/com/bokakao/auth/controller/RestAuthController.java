package com.bokakao.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bokakao.auth.service.AuthService;
import com.bokakao.member.domain.MemberMngDomain;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@Api(tags = {"RestAuthController Api v1 - 권한"})
@RestController
@Slf4j
@RequestMapping("/api/v1/auth")
public class RestAuthController {
	
	@Autowired
	private AuthService authService;
	
	@ApiOperation(value = "토큰 발행", notes = "<strong style='color:red;'>토큰</strong>을 발행한다.")
	@GetMapping("/kakao")
	public ResponseEntity<?> getAccessToken(@ApiParam(value = "코드", required = true) @RequestParam(required = true) String code) {
		String access_token = "";
		try {
			access_token = authService.getAccessToken(code);
			
		} catch (Exception e) {
			e.printStackTrace();
			new ResponseStatusException(HttpStatus.BAD_REQUEST, "저장에 실패 했습니다.");
		}
		
		return new ResponseEntity<>(access_token, HttpStatus.OK);
	}
	
	@ApiOperation(value = "로그인", notes = "<strong style='color:red;'>로그인</strong>를 등록한다.")
	@PostMapping("/kakao/login")
	public ResponseEntity<?> getUserInfo(@RequestBody MemberMngDomain memberMngDomain) {
		
		try {
			memberMngDomain = authService.getUserInfo(memberMngDomain);
			
		} catch (Exception e) {
			e.printStackTrace();
			new ResponseStatusException(HttpStatus.BAD_REQUEST, "저장에 실패 했습니다.");
		}
		
		return new ResponseEntity<>("", HttpStatus.OK);
	}
}
