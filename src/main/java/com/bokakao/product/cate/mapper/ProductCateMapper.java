package com.bokakao.product.cate.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProductCateMapper<T> {
	
	/**
     * 제품 카테고리 등록
     * @param T
     * @return
     * @throws Exception
     */
    public void insertProductMng(T param) throws Exception;
    
    /**
     * 제품 카테고리 삭제
     * @param String
     * @return
     * @throws Exception
     */
    public void deleteProductMng(String param) throws Exception;
}
