package com.bokakao.review.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class ReviewGoodDomain extends ReviewDomain {
	
	private String review_good;		// 리뷰 좋아요
}
