package com.comtec.standard.member.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.comtec.standard.member.vo.MemberVO;

public interface MemberMapper{
	/**
	 * 부서 목록 조회
	 * @param param 
	 * @return key
	 */
	public List<Map<String, Object>> getDeptList(HashMap<String, Object> parameter) throws Exception;
	
	/**
	 * 회원 정보 조회
	 * @param param 
	 * @return key
	 */
	public Map<String, Object> selectMemberInfo(HashMap<String, Object> parameter) throws Exception;
	
	/**
	 * 회원 정보 등록
	 * @param param 
	 * @return key
	 */
	public int insertMember(HashMap<String,Object> parameter) throws Exception;

	/**
	 * 회원 정보 조회
	 * @param param 
	 * @return key
	 */
	public List<MemberVO> memberList() throws Exception;
}
