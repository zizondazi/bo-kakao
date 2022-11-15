package com.bokakao.product.mng.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bokakao.product.mng.domain.ProductMngDomain;
import com.bokakao.product.mng.service.ProductMngService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api (tags = {"RestProductController Api v1 - 제품"})
@RestController
@RequestMapping("/api/v1/product")
public class RestProductController {
	
	@Autowired
	private ProductMngService productMngService;

	@ApiOperation(value = "제품 목록 전체 조회",  notes = "<strong style='color:red;'>제품</strong> 목록을 조회한다.")
	@GetMapping()
	public ResponseEntity<?> getProductList(HttpServletRequest request) {
		List<ProductMngDomain> prdt_list = new ArrayList<ProductMngDomain>();
		ProductMngDomain prdt = new ProductMngDomain();
		
		try {
			prdt.setPage_no(Integer.parseInt(request.getParameter("page_no")));
			prdt.setPage_size(Integer.parseInt(request.getParameter("paze_size")));
			
			prdt_list = productMngService.getProductList(prdt);
		}catch (Exception e) {
			
		}
		return new ResponseEntity<>(prdt_list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "제품 단건 조회", notes = "<strong style='color:red;'>제품</strong> 단건을 조회한다.")
	@ApiImplicitParam(
		name 		 = "prdt_seq", 
		value 		 = "제품 아이디",
		dataType 	 = "int",
		paramType 	 = "path",
		defaultValue = "0"
	)
	@GetMapping("/{prdt_seq}")
	public ResponseEntity<?> getProductDetail(@PathVariable(name = "prdt_seq") Integer prdt_seq) {
		
		ProductMngDomain prdt = new ProductMngDomain();
		try {
			prdt.setPrdt_seq(String.valueOf(prdt_seq));
			
		} catch (Exception e) {
			e.printStackTrace();
			new ResponseStatusException(HttpStatus.BAD_REQUEST, "cate_up_seq 값이 없습니다.");
		}
		return new ResponseEntity<>(prdt, HttpStatus.OK);
	}
	
}
