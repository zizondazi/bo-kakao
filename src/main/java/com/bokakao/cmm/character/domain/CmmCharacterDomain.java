package com.bokakao.cmm.character.domain;

import com.bokakao.util.domain.CommonDomain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class CmmCharacterDomain extends CommonDomain {
	
	private String char_seq;		// 캐릭터 아이디
	private String char_nm;			// 캐릭터 명
	private String char_cate_img;	// 캐릭터 카테고리 이미지
	private String char_info_img;	// 캐릭터 소개 이미지
	private String ka_star_info;	// 카스타그램 소개
	private String ka_thum_img;		// 카스타그램 썸네일 이미지
}
