package com.bokakao.product.character.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProductCharacterMapper<T> {
	
	/**
     * 제품 캐릭터 등록
     * @param T
     * @return
     * @throws Exception
     */
    public void insertProductCharacter(T param) throws Exception;
    
    /**
     * 제품 캐릭터 삭제
     * @param T
     * @return
     * @throws Exception
     */
    public void deleteProductCharacter(String param) throws Exception;
}
