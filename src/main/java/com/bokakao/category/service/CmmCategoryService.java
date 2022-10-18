package com.bokakao.category.service;

import com.bokakao.category.domain.CmmCategoryDomain;

public interface CmmCategoryService {
    
	/**
     * 카테고리 등록
     * @param CmmCategoryDomain
     * @return
     * @throws Exception
     */
    public void mergeCmmCategory(CmmCategoryDomain param) throws Exception;
}
