package com.bokakao.cmm.category.service;

import java.util.List;

import com.bokakao.cmm.category.domain.CmmCategoryDomain;

public interface CmmCategoryService {
    
	/**
     * 카테고리 등록
     * @param CmmCategoryDomain
     * @return
     * @throws Exception
     */
    public void mergeCmmCategory(CmmCategoryDomain param) throws Exception;
    
    /**
     * 카테고리 목록 조회
     * @param CmmCategoryDomain
     * @return
     * @throws Exception
     */
    public List<CmmCategoryDomain> getCmmCategoryList(CmmCategoryDomain param) throws Exception;
}
