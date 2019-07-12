package com.comtec.standard.member.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.comtec.standard.member.mapper.MemberMapper;
import com.comtec.standard.member.vo.MemberVO;

@Service
public class MemberService implements MemberMapper{
	//@Resource
	//MemberMapper memberMapper;
	
	@Resource
	private SqlSessionTemplate sqlSession;
	
	private final static String NAMESPACE = "com.main.resources.mybatis.mapper.MemberMapper";
	
	public List<MemberVO> memberList() throws Exception{
		return sqlSession.selectList(NAMESPACE+ "memberList");
		//return memberMapper.memberList();
	}

	@Override
	public int insertMember(HashMap<String, String> parameter) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
