package com.bokakao.review.domain;

import com.bokakao.util.domain.CommonDomain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class ReviewDomain extends CommonDomain {
	
	private String review_seq;			// 리뷰 아이디
	private String prdt_seq;			// 상품 아이디
	private String ord_num;				// 주문 번호
	private String review_score;		// 리뷰 점수
	private String review_con;			// 리뷰 내용
	
	private Integer review_good_cnt;	// 리뷰 좋아요 수
	private String good_yn;				// 리뷰 좋아요 여부
}
