package com.bokakao.auth.service;

import com.bokakao.member.domain.MemberMngDomain;

public interface AuthService {
	
	 /**
     * 카카오 로그인
     * @param 
     * @return
     * @throws Exception
     */
    public Integer getKaKaoLogin(MemberMngDomain memberMngDomain) throws Exception;
    
    /**
     * 토큰 조회
     * @param 
     * @return
     * @throws Exception
     */
    public String getAccessToken(String code) throws Exception;
    
    /**
     * 회원정보 조회
     * @param 
     * @return
     * @throws Exception
     */
    public MemberMngDomain getUserInfo(MemberMngDomain memberMngDomain) throws Exception;
}
