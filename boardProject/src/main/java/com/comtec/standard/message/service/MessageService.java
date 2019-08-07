package com.comtec.standard.message.service;

import java.util.*;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import com.comtec.standard.message.mapper.MessageMapper;

@Service
public class MessageService implements MessageMapper{
	@Resource
	private SqlSessionTemplate sqlSession;
	
	String prefix = "com.comtec.standard.message.mapper.MessageMapper";


	@Override
	public int insertMessage(HashMap<String, Object> parameter) throws Exception {
		int result = sqlSession.insert(prefix+".insertMessage", parameter);
		
		return result;
	}
}
