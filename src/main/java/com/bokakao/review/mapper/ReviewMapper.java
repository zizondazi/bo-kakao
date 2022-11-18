package com.bokakao.review.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ReviewMapper<T> {

	/**
     * 상품 별 리뷰 조회
     * @param T
     * @return List<T>
     * @throws Exception
     */
	public List<T> getReviewByProduct(T domain) throws Exception;
	
	/**
	 * 상품 별 리뷰 저장
	 * @param ReviewDomain
	 * @return
	 * @throws Exception
	 */
	public Integer setReivew(T domain) throws Exception;
}
