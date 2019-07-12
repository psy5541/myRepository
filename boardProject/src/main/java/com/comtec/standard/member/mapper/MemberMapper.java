package com.comtec.standard.member.mapper;

import java.util.HashMap;
import java.util.List;

import com.comtec.standard.member.vo.MemberVO;

public interface MemberMapper{
	/**
	 * 회원 정보 등록
	 * @param param 
	 * @return key
	 */
	public int insertMember(HashMap<String,String> parameter) throws Exception;

	/**
	 * 회원 정보 조회
	 * @param param 
	 * @return key
	 */
	public List<MemberVO> memberList() throws Exception;
}
