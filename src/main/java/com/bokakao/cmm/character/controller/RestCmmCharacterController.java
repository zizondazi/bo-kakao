package com.bokakao.cmm.character.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bokakao.cmm.category.domain.CmmCategoryDomain;
import com.bokakao.cmm.character.domain.CmmCharacterDomain;
import com.bokakao.cmm.character.service.CmmCharacterService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(tags = {"RestCmmCharacterController Api v1"})
@RestController
@RequestMapping("/api/v1/cmm/character")
@Slf4j
public class RestCmmCharacterController {

	@Autowired
	private CmmCharacterService cmmCharacterService;
	
	@ApiOperation(value = "캐릭터 목록 조회", notes = "<strong style='color:red;'>캐릭터</strong> 목록을 조회한다.")
	@GetMapping()
	public ResponseEntity<?> getCmmCharacterList() {
		
		List<CmmCharacterDomain> char_list = null;
		try {
			char_list = cmmCharacterService.getCmmCharacter(new CmmCharacterDomain());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(char_list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "캐틱터 조회", notes = "<strong style='color:red;'>캐틱터</strong>를 조회한다.")
	@ApiImplicitParam(
		name 		 = "char_seq", 
		value 		 = "캐릭터 아이디",
		dataType 	 = "int",
		paramType 	 = "path",
		defaultValue = "0"
	)
	@GetMapping("/{char_seq}")
	public ResponseEntity<?> getCmmCharacter(@PathVariable(name = "char_seq") Integer char_seq) {
		
		List<CmmCharacterDomain> char_list = null;
		try {
			CmmCharacterDomain character = new CmmCharacterDomain();
			character.setChar_seq(String.valueOf(char_seq));
			char_list = cmmCharacterService.getCmmCharacter(character);
		} catch (Exception e) {
			e.printStackTrace();
			new ResponseStatusException(HttpStatus.BAD_REQUEST, "cate_up_seq 값이 없습니다.");
		}
		return new ResponseEntity<>(char_list, HttpStatus.OK);
	}

}
