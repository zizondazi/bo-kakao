package com.bokakao.product.mng.service;

import java.util.List;

import com.bokakao.product.cate.domain.ProductCateDomain;
import com.bokakao.product.mng.domain.ProductMngDomain;

public interface ProductMngService {
    
	/**
     * 제품 등록
     * @param ProductMngDomain
     * @return
     * @throws Exception
     */
    public void mergeProductMng(ProductMngDomain mng, List<ProductCateDomain> cate_list) throws Exception;
}
