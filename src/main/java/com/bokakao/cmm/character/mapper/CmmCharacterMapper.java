package com.bokakao.cmm.character.mapper;

import java.util.List;

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
    
    /**
     * 캐릭터 목록 조회
     * @param 
     * @return
     * @throws Exception
     */
    public List<T> getCmmCharacter(T param) throws Exception;
}
