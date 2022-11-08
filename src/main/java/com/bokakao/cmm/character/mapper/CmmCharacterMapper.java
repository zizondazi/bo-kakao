package com.bokakao.cmm.character.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CmmCharacterMapper<T> {
	
	/**
     * 캐릭터 등록
     * @param CmmCharacterDomain
     * @return
     * @throws Exception
     */
    public void mergeCmmCharacter(T param) throws Exception;
}
