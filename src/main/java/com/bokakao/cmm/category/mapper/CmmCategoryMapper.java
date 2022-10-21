package com.bokakao.cmm.category.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CmmCategoryMapper<T> {
	
	/**
     * 카테고리 등록
     * @param CmmCategoryDomain
     * @return
     * @throws Exception
     */
    public void mergeCmmCategory(T param) throws Exception;
    
    /**
     * 카테고리 목록 조회
     * @param Integer up_cate_seq
     * @return
     * @throws Exception
     */
    public List<T> getCmmCategoryList(Integer up_cate_seq) throws Exception;
}
