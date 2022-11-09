package com.bokakao.cmm.character.service;

import java.util.List;

import com.bokakao.cmm.character.domain.CmmCharacterDomain;

public interface CmmCharacterService {
    
    /**
     * 캐릭터 목록 조회
     * @param 
     * @return
     * @throws Exception
     */
    public List<CmmCharacterDomain> getCmmCharacter(CmmCharacterDomain param) throws Exception;
}
