package com.bokakao.review.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bokakao.review.domain.ReviewDomain;
import com.bokakao.review.mapper.ReviewMapper;
import com.bokakao.review.service.ReviewService;

@Service("ReviewService")
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewMapper<ReviewDomain> reviewMapper;
	
	/**
     * 상품 별 리뷰 조회
     * @param ReviewDomain
     * @return
     * @throws Exception
     */
	@Override
	public List<ReviewDomain> getReviewByProduct(ReviewDomain reviewDomain) throws Exception {
		return reviewMapper.getReviewByProduct(reviewDomain);
	}
	
	/**
	 * 상품 별 리뷰 저장
	 * @param ReviewDomain
	 * @return
	 * @throws Exception
	 */
	@Override
	public Integer setReivew(ReviewDomain reviewDomain) throws Exception {
		return reviewMapper.setReivew(reviewDomain);
	}
}
