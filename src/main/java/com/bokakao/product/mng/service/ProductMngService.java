package com.bokakao.product.mng.service;

import java.util.List;

import com.bokakao.product.cate.domain.ProductCateDomain;
import com.bokakao.product.character.domain.ProductCharacterDomain;
import com.bokakao.product.mng.domain.ProductMngDomain;

public interface ProductMngService {
    
	/**
     * 카테고리 별 제품 등록
     * @param ProductMngDomain
     * @return
     * @throws Exception
     */
    public void mergeProductMngByCategory(ProductMngDomain mng, List<ProductCateDomain> cate_list) throws Exception;
    
    /**
     * 캐릭터 별 제품 등록
     * @param ProductMngDomain
     * @return
     * @throws Exception
     */
    public void mergeProductMngByCharacter(ProductMngDomain mng, ProductCharacterDomain prdt_char) throws Exception;
    
    /**
     * 제품 상세 등록
     * @param ProductMngDomain
     * @return
     * @throws Exception
     */
    public void mergeProductDtl(ProductMngDomain mng, List<ProductMngDomain> img_list) throws Exception;
    
    /**
     * 제품 목록 조회
     * @param ProductMngDomain
     * @return List<ProductMngDomain>
     * @throws Exception
     */
    public List<ProductMngDomain> getProductList(ProductMngDomain mng) throws Exception;
    
    /**
     * 제품 상세 조회
     * @param ProductMngDomain
     * @return ProductMngDomain
     * @throws Exception
     */
    public ProductMngDomain getProduct(ProductMngDomain mng) throws Exception;
}
