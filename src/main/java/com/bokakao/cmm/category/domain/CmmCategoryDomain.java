package com.bokakao.cmm.category.domain;

import com.bokakao.util.domain.CommonDomain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class CmmCategoryDomain extends CommonDomain{
	
	private String cate_seq;		// 카테고리 seq
	private String cate_nm;			// 카테고리 명
	private String cate_up_seq;		// 상위 카테고리 seq
}
