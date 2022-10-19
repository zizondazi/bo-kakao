package com.bokakao.cmm.category.service.impl;

import org.springframework.stereotype.Service;

import com.bokakao.cmm.category.domain.CmmCategoryDomain;
import com.bokakao.cmm.category.mapper.CmmCategoryMapper;
import com.bokakao.cmm.category.service.CmmCategoryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("CmmCategoryService")
public class CmmCategoryServiceImpl implements CmmCategoryService {
	
	//@Resource(name="CmmCategoryMapper")
	private CmmCategoryMapper<CmmCategoryDomain> cmmCategoryMapper;
	
	/**
     * 카테고리 등록
     * @param CmmCategoryDomain
     * @return
     * @throws Exception
     */
	@Override
	public void mergeCmmCategory(CmmCategoryDomain param) throws Exception {
		cmmCategoryMapper.mergeCmmCategory(param);
	}

}
