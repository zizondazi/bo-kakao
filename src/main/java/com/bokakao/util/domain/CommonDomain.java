package com.bokakao.util.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CommonDomain {
	
	private String uid;				// 회원 id
	private String reg_uid;			// 등록자 id
	private String reg_dtm;			// 등록일
	private String mdf_uid;			// 수정자 id
	private String mdf_dtm;			// 수정일
	
	/* 검색 조건 */
	private String sch_cate_seq;	// 검색 - 카테고리 seq	
}
