package com.bokakao.product.mng.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bokakao.product.mng.domain.ProductMngDomain;
import com.bokakao.product.mng.service.ProductMngService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api (tags = {"RestProductController Api v1 - 제품"})
@RestController
@RequestMapping("/api/v1/products")
public class RestProductController {
	
	@Autowired
	private ProductMngService productMngService;

	@ApiOperation(value = "제품 목록 전체 조회",  notes = "<strong style='color:red;'>제품</strong> 목록을 조회한다.")
	@GetMapping()
	public ResponseEntity<?> getProductList(
			@ApiParam(value = "페이지 번호", required = false) @RequestParam(required = false) Integer page_no,
			@ApiParam(value = "페이지 사이즈", required = false) @RequestParam(required = false) Integer page_size) {
		List<ProductMngDomain> prdt_list = new ArrayList<ProductMngDomain>();
		ProductMngDomain prdt = new ProductMngDomain();
		
		try {
			if(page_no != null) prdt.setPage_no(page_no);
			if(page_size != null) prdt.setPage_size(page_size);
			
			prdt_list = productMngService.getProductList(prdt);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(prdt_list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "제품 상세 조회", notes = "<strong style='color:red;'>제품</strong> 상세를 조회한다.")
	@ApiImplicitParam(
		name 		 = "prdt_seq", 
		value 		 = "제품 아이디",
		dataType 	 = "int",
		paramType 	 = "path",
		example 	 = "0"
	)
	@GetMapping("/{prdt_seq}")
	public ResponseEntity<?> getProductDetail(@PathVariable(name = "prdt_seq") Integer prdt_seq) {
		ProductMngDomain prdt = new ProductMngDomain();
		try {
			prdt.setPrdt_seq(String.valueOf(prdt_seq));
			prdt.setUid("1");
			prdt = productMngService.getProduct(prdt);
			
		} catch (Exception e) {
			e.printStackTrace();
			new ResponseStatusException(HttpStatus.BAD_REQUEST, "cate_up_seq 값이 없습니다.");
		}
		return new ResponseEntity<>(prdt, HttpStatus.OK);
	}
	
	@ApiOperation(value = "카테고리 별 제품 목록 조회", notes = "<strong style='color:red;'>카테고리 별 제품</strong> 목록을 조회한다.")
	@ApiImplicitParam(
		name 		 = "cate_seq", 
		value 		 = "카테고리 아이디",
		dataType 	 = "int",
		paramType 	 = "path",
		example 	 = "0"
	)
	@GetMapping("/cate/{cate_seq}")
	public ResponseEntity<?> getProductDetailByCate(
			@PathVariable(name = "cate_seq") Integer cate_seq,
			@ApiParam(value = "페이지 번호", required = false) @RequestParam(required = false) Integer page_no,
			@ApiParam(value = "페이지 사이즈", required = false) @RequestParam(required = false) Integer page_size
			) {
		List<ProductMngDomain> prdt_list = new ArrayList<ProductMngDomain>();
		ProductMngDomain prdt = new ProductMngDomain();
		
		try {
			if(page_no != null) prdt.setPage_no(page_no);
			if(page_size != null) prdt.setPage_size(page_size);
			prdt.setSch_cate_seq(String.valueOf(cate_seq));
			
			prdt_list = productMngService.getProductList(prdt);
		} catch (Exception e) {
			new ResponseStatusException(HttpStatus.BAD_REQUEST, "cate_up_seq 값이 없습니다.");
		}
		return new ResponseEntity<>(prdt_list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "캐릭터 별 제품 목록 조회", notes = "<strong style='color:red;'>캐릭터 별 제품</strong> 목록을 조회한다.")
	@ApiImplicitParam(
			name 		 = "char_seq", 
			value 		 = "캐릭터 아이디",
			dataType 	 = "int",
			paramType 	 = "path",
			example 	 = "0"
			)
	@GetMapping("/char/{char_seq}")
	public ResponseEntity<?> getProductDetailByCharacter(
			@PathVariable(name = "char_seq") Integer char_seq,
			@ApiParam(value = "페이지 번호", required = false) @RequestParam(required = false) Integer page_no,
			@ApiParam(value = "페이지 사이즈", required = false) @RequestParam(required = false) Integer page_size
			) {
		List<ProductMngDomain> prdt_list = new ArrayList<ProductMngDomain>();
		ProductMngDomain prdt = new ProductMngDomain();
		
		try {
			if(page_no != null) prdt.setPage_no(page_no);
			if(page_size != null) prdt.setPage_size(page_size);
			prdt.setSch_char_seq(String.valueOf(char_seq));
			
			prdt_list = productMngService.getProductList(prdt);
		} catch (Exception e) {
			new ResponseStatusException(HttpStatus.BAD_REQUEST, "cate_up_seq 값이 없습니다.");
		}
		return new ResponseEntity<>(prdt_list, HttpStatus.OK);
	}
}
