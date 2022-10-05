package com.bokakao.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api (tags = {"RestProductController Api v1"})
@RestController
@RequestMapping("/api/v1/product")
public class RestProductController {

	@ApiOperation(value="테스트용", notes="테스트 입니다.")
	@GetMapping("/test")
	public void test() {
		System.out.println("!!!!!!!!!!!!!!!! 성공!");
	}
	
}
