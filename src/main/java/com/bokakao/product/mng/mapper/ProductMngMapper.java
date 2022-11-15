package com.bokakao.product.mng.mapper;

import java.util.List;

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
    
    /**
     * 제품 이미지 등록
     * @param T
     * @return
     * @throws Exception
     */
    public void mergeProductImg(T param) throws Exception;
    
    /**
     * 제품 이미지 삭제
     * @param T
     * @return
     * @throws Exception
     */
    public void deleteProductImg(T param) throws Exception;
    
    /**
     * 제품 목록 조회
     * @param T
     * @return List<T>
     * @throws Exception
     */
    public List<T> getProductList(T param) throws Exception;
}
