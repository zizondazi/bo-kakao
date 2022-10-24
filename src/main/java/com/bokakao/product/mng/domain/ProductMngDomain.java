package com.bokakao.product.mng.domain;

import com.bokakao.util.domain.CommonDomain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class ProductMngDomain extends CommonDomain {
	
	private String prdt_seq;			// 제품 seq
	private String prdt_nm;				// 제품명
	private Integer prdt_price;			// 제품가격
	private Integer prdt_sale;			// 제품세일율
	private String prdt_img;			// 제품이미지 - 주소형태
	private Integer prdt_stock;			// 재품재고
	private String prdt_character;		// 재품캐릭터 - 멀티 캐릭터 지정 가능으로 테이블 따로 뺌
	private String prdt_season;			// 제품시즌
	private String prdt_dtl;			// 제품상세
	private String prdt_info;			// 제품설명
	private String korea_shipping_yn;	// 국내배송 가능여부
	private String intl_shipping_yn;	// 해외배송 가능여부
	
}
