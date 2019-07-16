package com.comtec.standard.member.service;

import java.util.HashMap;
import java.util.List;

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
	
	@Resource
	private DataSourceTransactionManager transactionManager;
	
	public List<MemberVO> memberList() throws Exception{
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		return memberMapper.memberList();
	}

	@Override
	@Transactional
	public String insertMember(HashMap<String, Object> parameter) throws Exception {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName("example.transaction");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
			memberMapper.insertMember(parameter);
		} catch(Exception e) {
			transactionManager.rollback(status);
			e.printStackTrace();
			return "FAIL";
		} finally {
			sqlSession.close();
		}
		
		transactionManager.commit(status);
		return "SU";
	}
}
