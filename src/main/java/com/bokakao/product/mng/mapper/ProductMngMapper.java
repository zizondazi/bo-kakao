package com.bokakao.product.mng.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProductMngMapper<T> {
	
	/**
     * 제품 등록
     * @param T
     * @return
     * @throws Exception
     */
    public void mergeProductMng(T param) throws Exception;
}
