package com.bokakao.product.character.domain;

import com.bokakao.util.domain.CommonDomain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class ProductCharacterDomain extends CommonDomain {
	
	private String prdt_seq;			// 제품 seq
	private String cate_seq; 			// 카테고리 seq
}
