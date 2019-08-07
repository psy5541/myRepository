package com.comtec.standard.member.service;

import java.util.*;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.comtec.standard.member.mapper.MemberMapper;
import com.comtec.standard.member.vo.MemberVO;

@Service
public class MemberService implements MemberMapper{
	@Resource
	private SqlSessionTemplate sqlSession;
	
	String prefix = "com.comtec.standard.member.mapper.MemberMapper";

	@Override
	public List<Map<String, Object>> getDeptList(HashMap<String, Object> parameter) throws Exception{
		List<Map<String, Object>> map = sqlSession.selectList(prefix+".getDeptList", parameter);
		return map;
	}

	@Override
	public Map<String, Object> selectMemberInfo(HashMap<String, Object> parameter) throws Exception{
		Map<String, Object> map = sqlSession.selectOne(prefix+".selectMemberInfo", parameter);
		if(map == null) map = new HashMap<String, Object>();
		return map;
	}

	@Override
	public List<MemberVO> memberList() throws Exception{
		//MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		return sqlSession.selectList(prefix+".memberList");
	}

	@Override
	public int insertMember(HashMap<String, Object> parameter) throws Exception {
		int result = sqlSession.insert(prefix+".insertMember", parameter);
		
		return result;
	}
}
