package com.comtec.standard.system.service;

import java.util.*;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import com.comtec.standard.system.mapper.SystemMapper;

@Service
public class SystemService implements SystemMapper{
	@Resource
	private SqlSessionTemplate sqlSession;
	
	String prefix = "com.comtec.standard.system.mapper.SystemMapper";


	@Override
	public List<Map<String, Object>> getCodeList(HashMap<String, Object> parameter) throws Exception {
		List<Map<String, Object>> result = sqlSession.selectList(prefix+".getCodeList", parameter);
		
		return result;
	}
}
