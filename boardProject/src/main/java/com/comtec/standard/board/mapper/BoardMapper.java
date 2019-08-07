package com.comtec.standard.board.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BoardMapper{
	/**
	 * 게시글 등록
	 * @param param 
	 * @return key
	 */
	public int insertBoard(HashMap<String,Object> parameter) throws Exception;
	
	/**
	 * 게시글 수정
	 * @param param 
	 * @return list
	 */
	public int updateBoard(HashMap<String,Object> parameter) throws Exception;
	
	/**
	 * 게시글 삭제
	 * @param param 
	 * @return list
	 */
	public int deleteBoard(String seq) throws Exception;
	
	/**
	 * 게시글 목록 조회
	 * @param param 
	 * @return list
	 */
	public List<Map<String, Object>> getBoardList(HashMap<String,Object> parameter) throws Exception;
	
	/**
	 * 게시글 조회수 증가
	 * @param param 
	 * @return list
	 */
	public int updateViewCnt(String seq) throws Exception;
	
	/**
	 * 코멘트 등록
	 * @param param 
	 * @return list
	 */
	public int insertComment(HashMap<String,Object> parameter) throws Exception;
	
	/**
	 * 해당 게시글 코멘트 목록 조회
	 * @param param 
	 * @return list
	 */
	public List<Map<String, Object>> getCommentList(HashMap<String,Object> parameter) throws Exception;
	
	/**
	 * 게시글 현재기준 월별 조회
	 * @param param 
	 * @return list
	 */
	public List<Map<String, Object>> getBoardLChart(HashMap<String,Object> parameter) throws Exception;
	
	/**
	 * 게시글 현재기준 월별 조회
	 * @param param 
	 * @return list
	 */
	public List<Map<String, Object>> getBoardBChart(HashMap<String,Object> parameter) throws Exception;
	
	/**
	 * 게시글 별점 등록
	 * @param param 
	 * @return list
	 */
	public int insertRating(HashMap<String,Object> parameter) throws Exception;
}
