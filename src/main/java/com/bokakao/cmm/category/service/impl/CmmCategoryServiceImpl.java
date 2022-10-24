package com.bokakao.cmm.category.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bokakao.cmm.category.domain.CmmCategoryDomain;
import com.bokakao.cmm.category.mapper.CmmCategoryMapper;
import com.bokakao.cmm.category.service.CmmCategoryService;

@Service("CmmCategoryService")
public class CmmCategoryServiceImpl implements CmmCategoryService {
	
	@Autowired
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
	
    /**
     * 카테고리 목록 조회
     * @param CmmCategoryDomain
     * @return
     * @throws Exception
     */
	@Override
	public List<CmmCategoryDomain> getCmmCategoryList(CmmCategoryDomain param) throws Exception {
		return cmmCategoryMapper.getCmmCategoryList(param);
	}

}
