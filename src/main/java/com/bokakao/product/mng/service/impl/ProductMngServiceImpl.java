package com.bokakao.product.mng.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bokakao.product.cate.domain.ProductCateDomain;
import com.bokakao.product.cate.mapper.ProductCateMapper;
import com.bokakao.product.mng.domain.ProductMngDomain;
import com.bokakao.product.mng.mapper.ProductMngMapper;
import com.bokakao.product.mng.service.ProductMngService;

@Service("ProductMngService")
public class ProductMngServiceImpl implements ProductMngService {
	
	@Autowired
	private ProductMngMapper<ProductMngDomain> productMngMapper;
	
	@Autowired
	private ProductCateMapper<ProductCateDomain> productCateMapper;
	
	
	/**
     * 제품 등록
     * @param ProductMngDomain
     * @return
     * @throws Exception
     */
	@Override
	@Transactional 
	public void mergeProductMng(ProductMngDomain mng, List<ProductCateDomain> cate_list) throws Exception {
		// 제품 등록
		productMngMapper.mergeProductMng(mng);
		
		// 제품 카테고리 삭제
		productCateMapper.deleteProductMng(mng.getPrdt_seq());
		
		// 제품 카테고리 등록
		for(ProductCateDomain cate : cate_list) {
			productCateMapper.insertProductMng(cate);
		}
	}
}