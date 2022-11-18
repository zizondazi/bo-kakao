package com.bokakao.review.service;

import java.util.List;

import com.bokakao.review.domain.ReviewDomain;

public interface ReviewService {
	
	/**
     * 상품 별 리뷰 조회
     * @param ReviewDomain
     * @return
     * @throws Exception
     */
	public List<ReviewDomain> getReviewByProduct(ReviewDomain reviewDomain) throws Exception;
	
	/**
	 * 상품 별 리뷰 저장
	 * @param ReviewDomain
	 * @return
	 * @throws Exception
	 */
	public Integer setReivew(ReviewDomain reviewDomain) throws Exception;
}
