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
    public void insertProductCate(T param) throws Exception;
    
    /**
     * 제품 카테고리 삭제
     * @param String
     * @return
     * @throws Exception
     */
    public void deleteProductCate(String param) throws Exception;
}
