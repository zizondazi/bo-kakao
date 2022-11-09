package com.bokakao.cmm.character.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bokakao.cmm.character.domain.CmmCharacterDomain;
import com.bokakao.cmm.character.mapper.CmmCharacterMapper;
import com.bokakao.cmm.character.service.CmmCharacterService;

@Service("CmmCharacterService")
public class CmmCharacterServiceImpl implements CmmCharacterService {
	
	@Autowired
	private CmmCharacterMapper<CmmCharacterDomain> cmmCharacterMapper;

    /**
     * 캐릭터 목록 조회
     * @param CmmCharacterDomain
     * @return
     * @throws Exception
     */
	@Override
	public List<CmmCharacterDomain> getCmmCharacter(CmmCharacterDomain param) throws Exception {
		return cmmCharacterMapper.getCmmCharacter(param);
	}
	

}
