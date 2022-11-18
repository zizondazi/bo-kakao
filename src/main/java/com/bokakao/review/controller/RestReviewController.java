package com.bokakao.review.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bokakao.review.domain.ReviewDomain;
import com.bokakao.review.service.ReviewService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = {"RestReviewController Api v1 -  리뷰"})
@RestController
@RequestMapping("/api/v1/reviews")
public class RestReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	@ApiOperation(value = "제품 별 리뷰 조회", notes = "<strong style='color:red;'>제품 별 리뷰</strong>를 조회한다.")
	@ApiImplicitParam(
		name 		 = "prdt_seq", 
		value 		 = "제품 아이디",
		dataType 	 = "int",
		paramType 	 = "path",
		example 	 = "0"
	)
	@GetMapping("/{prdt_seq}")
	public ResponseEntity<?> getReviewByPrdt(
			@PathVariable(name = "prdt_seq") Integer prdt_seq,
			@ApiParam(value = "페이지 번호", required = false) @RequestParam(required = false) Integer page_no,
			@ApiParam(value = "페이지 사이즈", required = false) @RequestParam(required = false) Integer page_size) {
		
		List<ReviewDomain> review_list = null;
		ReviewDomain review = new ReviewDomain();
		
		try {
			if(page_no != null) review.setPage_no(page_no);
			if(page_size != null) review.setPage_size(page_size);
			review.setPrdt_seq(String.valueOf(prdt_seq));
			review_list = reviewService.getReviewByProduct(review);
			
		} catch (Exception e) {
			new ResponseStatusException(HttpStatus.BAD_REQUEST, "prdt_seq 값이 없습니다.");
		}
		return new ResponseEntity<>(review_list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "제품 별 리뷰 등록", notes = "<strong style='color:red;'>제품 별 리뷰</strong>를 등록한다.")
	@PostMapping()
	public ResponseEntity<?> setReivew(@RequestBody ReviewDomain reviewDomain) {
		
		try {
			
			reviewService.setReivew(reviewDomain);
			
		} catch (Exception e) {
			new ResponseStatusException(HttpStatus.BAD_REQUEST, "저장에 실패 했습니다.");
		}
		
		return new ResponseEntity<>("", HttpStatus.OK);
	}
}
