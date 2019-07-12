package com.comtec.standard.member.service;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.comtec.standard.member.mapper.MemberMapper;
import com.comtec.standard.member.vo.MemberVO;

@Service
public class MemberService {
	@Resource
	MemberMapper memberMapper;
	
	//@Resource
	//@Qualifier("sqlSession")
	//private SqlSessionTemplate sqlSession;
	
	//private final static String NAMESPACE = "com.comtec.standard.db.mapper.WebConfig";
	
	public List<MemberVO> memberList() throws Exception{
		//return sqlSession.selectList(NAMESPACE+ "memberList");
		return memberMapper.memberList();
	}
	
}
