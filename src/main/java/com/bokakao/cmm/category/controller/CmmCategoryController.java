package com.bokakao.cmm.category.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bokakao.cmm.category.domain.CmmCategoryDomain;
import com.bokakao.cmm.category.service.CmmCategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(tags = {"CmmCategoryController Api v1"})
@RestController
@RequestMapping("/api/v1/cmm/cate")
@Slf4j
public class CmmCategoryController {

	@Autowired
	private CmmCategoryService cmmCategoryService;
	
	@ApiOperation(value = "카테고리 목록 조회", notes = "<strong style='color:red;'>카테고리</strong> 목록을 조회한다.")
	@ApiImplicitParam(
		name 		 = "cate_up_seq", 
		value 		 = "상위 카테고리 아이디",
		dataType 	 = "int",
		paramType 	 = "path",
		defaultValue = "0"
	)
	@GetMapping("/{cate_up_seq}")
	public ResponseEntity<?> getCmmCategoryList(@PathVariable(name = "cate_up_seq") Integer cate_up_seq) {
		
		List<CmmCategoryDomain> cate_list = null;
		try {
			cate_list = cmmCategoryService.getCmmCategoryList(cate_up_seq);
		} catch (Exception e) {
			e.printStackTrace();
			new ResponseStatusException(HttpStatus.BAD_REQUEST, "cate_up_seq 값이 없습니다.");
		}
		
		return new ResponseEntity<>(cate_list, HttpStatus.OK);
	}
}
