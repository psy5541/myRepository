package com.comtec.standard.board.service;

import java.util.*;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import com.comtec.standard.board.mapper.BoardMapper;

@Service
public class BoardService implements BoardMapper{
	@Resource
	private SqlSessionTemplate sqlSession;
	
	String prefix = "com.comtec.standard.board.mapper.BoardMapper";


	@Override
	public int insertBoard(HashMap<String, Object> parameter) throws Exception {
		int result = sqlSession.insert(prefix+".insertBoard", parameter);
		
		return result;
	}

	@Override
	public int updateBoard(HashMap<String, Object> parameter) throws Exception {
		int result = sqlSession.insert(prefix+".updateBoard", parameter);
		
		return result;
	}

	@Override
	public int deleteBoard(String seq) throws Exception {
		int result = sqlSession.delete(prefix+".deleteBoard", seq);
		
		return result;
	}

	@Override
	public List<Map<String, Object>> getBoardList(HashMap<String, Object> parameter) throws Exception {
		List<Map<String, Object>> result = sqlSession.selectList(prefix+".getBoardList", parameter);
		
		return result;
	}
	
	@Override
	public int updateViewCnt(String seq) throws Exception {
		int result = sqlSession.update(prefix+".updateViewCnt", seq);
		
		return result;
	}

	@Override
	public int insertComment(HashMap<String, Object> parameter) throws Exception {
		int result = sqlSession.insert(prefix+".insertComment", parameter);
		
		return result;
	}

	@Override
	public List<Map<String, Object>> getCommentList(HashMap<String, Object> parameter) throws Exception {
		List<Map<String, Object>> result = sqlSession.selectList(prefix+".getCommentList", parameter);
		
		return result;
	}

	@Override
	public List<Map<String, Object>> getBoardLChart(HashMap<String, Object> parameter) throws Exception {
		List<Map<String, Object>> result = sqlSession.selectList(prefix+".getBoardLChart", parameter);
		
		return result;
	}

	@Override
	public List<Map<String, Object>> getBoardBChart(HashMap<String, Object> parameter) throws Exception {
		List<Map<String, Object>> result = sqlSession.selectList(prefix+".getBoardBChart", parameter);
		
		return result;
	}

	@Override
	public int insertRating(HashMap<String, Object> parameter) throws Exception {
		int result = sqlSession.insert(prefix+".insertRating", parameter);
		
		return result;
	}
}
