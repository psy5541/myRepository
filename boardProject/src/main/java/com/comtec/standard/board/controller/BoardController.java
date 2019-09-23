package com.comtec.standard.board.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.comtec.standard.board.service.BoardService;

@Controller
public class BoardController{
	@Resource
	BoardService boardService;
	
	/**
	 * 게시판 목록 이동
	 * @return url
	 */
	@RequestMapping(value="/boardList") 
	public String boardList() throws Exception{
		return "board/boardList";
	}
	
	/**
	 * 게시판 작성 이동
	 * @return url
	 */
	@RequestMapping(value="/boardRegist") 
	public String boardRegist() throws Exception{
		return "board/boardRegist";
	}
	
	/**
	 * 게시판 작성 이동
	 * @return url
	 */
	@RequestMapping(value="/boardDetail") 
	public String boardDetail(@RequestParam String seq, HttpServletRequest request, Model model) throws Exception{
		int updateCnt = boardService.updateViewCnt(seq);

		if(updateCnt > 0) {
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("seq", seq);
			
			HttpSession session = request.getSession();
			Map<String, Object> loginInfo = (Map<String, Object>) session.getAttribute("loginInfo");
			map.put("userId", loginInfo.get("userId"));
			
			List<Map<String, Object>> result = boardService.getBoardList(map);
			for(Map<String, Object> data : result) {
				model.addAttribute("seq", data.get("seq"));
				model.addAttribute("boardType", data.get("boardType"));
				model.addAttribute("title", data.get("title"));
				model.addAttribute("contents", data.get("contents"));
				model.addAttribute("userNm", data.get("userNm"));
				model.addAttribute("noticeYn", data.get("noticeYn"));
				model.addAttribute("ratingCheck", data.get("ratingCheck"));
			}
		}
		
		return "board/boardDetail";
	}
	
	/**
	 * 게시판 별점 이동
	 * @return url
	 */
	@RequestMapping(value="/boardRating") 
	public String boardRating(@RequestParam(name="seq", required=false) String seq, Model model) throws Exception{
		model.addAttribute("seq", seq);
		return "board/boardRating";
	}
	
	/**
	 * 게시글 별점 등록
	 * @param parameter HTTP 요청 파라미터
	 * @return key
	 */
	@RequestMapping(value="/insertRating", method= {RequestMethod.POST}) 
	public @ResponseBody int insertRating(HttpServletRequest parameter, Model model) throws Exception{
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("seq", parameter.getParameter("seq"));
		map.put("rating", parameter.getParameter("rating"));
		
		HttpSession session = parameter.getSession();
		Map<String, Object> loginInfo = (Map<String, Object>) session.getAttribute("loginInfo");
		map.put("userId", loginInfo.get("userId"));
		
		int result = boardService.insertRating(map);
		return result;
 	}
	
	/**
	 * 게시글 등록
	 * @param parameter HTTP 요청 파라미터
	 * @return key
	 */
	@RequestMapping(value="/insertBoard", method= {RequestMethod.POST}) 
	public @ResponseBody int insertBoard(HttpServletRequest parameter, Model model) throws Exception{
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("boardType", parameter.getParameter("boardType"));
		map.put("title", parameter.getParameter("title"));
		map.put("contents", parameter.getParameter("contents"));
		map.put("noticeYn", parameter.getParameter("noticeYn"));
		
		HttpSession session = parameter.getSession();
		Map<String, Object> loginInfo = (Map<String, Object>) session.getAttribute("loginInfo");
		map.put("userId", loginInfo.get("userId"));
		
		int result = boardService.insertBoard(map);
		return result;
 	}
	
	/**
	 * 게시글 수정
	 * @param parameter HTTP 요청 파라미터
	 * @return key
	 */
	@RequestMapping(value="/updateBoard", method= {RequestMethod.POST}) 
	public @ResponseBody int updateBoard(HttpServletRequest parameter, Model model) throws Exception{
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("seq", parameter.getParameter("seq"));
		map.put("boardType", parameter.getParameter("boardType"));
		map.put("title", parameter.getParameter("title"));
		map.put("contents", parameter.getParameter("contents"));
		map.put("noticeYn", parameter.getParameter("noticeYn"));
		
		int result = boardService.updateBoard(map);
		return result;
 	}
	
	/**
	 * 게시판 작성 삭제
	 * @param parameter String
	 * @return url
	 */
	@RequestMapping(value="/deleteBoard") 
	public @ResponseBody int deleteBoard(@RequestParam String seq) throws Exception{
		int result = boardService.deleteBoard(seq);
		return result;
	}
	
	/**
	 * 게시글 조회
	 * @param parameter HTTP 요청 파라미터
	 * @return list
	 */
	@RequestMapping(value = "/getBoardList", method=RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getBoardList(@RequestParam Map<String, Object> parameter) throws Exception{
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("title", parameter.get("title"));
		map.put("page", parameter.get("page"));
		map.put("rows", parameter.get("rows"));
		
		List<Map<String, Object>> result = boardService.getBoardList(map);
		return result;
	}
	
	/**
	 * 코멘트 등록 -> 코멘트 조회
	 * @param parameter HTTP 요청 파라미터
	 * @return list
	 */
	@RequestMapping(value="/insertComment", method= {RequestMethod.POST}) 
	public @ResponseBody List<Map<String, Object>> insertComment(HttpServletRequest parameter) throws Exception{
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("seqBoard", parameter.getParameter("seq"));
		map.put("dataComment", parameter.getParameter("dataComment"));
		
		HttpSession session = parameter.getSession();
		Map<String, Object> loginInfo = (Map<String, Object>) session.getAttribute("loginInfo");
		map.put("userId", loginInfo.get("userId"));
		
		//코멘트 등록
		int seqComment = boardService.insertComment(map);
		map.put("seqComment", seqComment);
		
		//코멘트 목록 조회
		List<Map<String, Object>> result = boardService.getCommentList(map);
		return result;
 	}
	
	/**
	 * 게시글 코멘트 목록 조회
	 * @param parameter HTTP 요청 파라미터
	 * @return list
	 */
	@RequestMapping(value="/getCommentList", method= {RequestMethod.POST}) 
	public @ResponseBody List<Map<String, Object>> getCommentList(HttpServletRequest parameter) throws Exception{
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("seqBoard", parameter.getParameter("seq"));
		
		//코멘트 목록 조회
		List<Map<String, Object>> result = boardService.getCommentList(map);
		return result;
 	}
	
	/**
	 * 게시글 현재기준 월별 조회
	 * @param parameter HTTP 요청 파라미터
	 * @return list
	 */
	@RequestMapping(value="/getBoardLChart", method= {RequestMethod.POST}) 
	public @ResponseBody List<Map<String, Object>> getBoardLChart(HttpServletRequest parameter) throws Exception{
		HashMap<String,Object> map = new HashMap<String,Object>();
		
		//게시글 현재기준 월별 조회
		List<Map<String, Object>> result = boardService.getBoardLChart(map);
		return result;
 	}
	
	/**
	 * 게시글 현재기준 월별 조회
	 * @param parameter HTTP 요청 파라미터
	 * @return list
	 */
	@RequestMapping(value="/getBoardBChart", method= {RequestMethod.POST}) 
	public @ResponseBody List<Map<String, Object>> getBoardBChart(HttpServletRequest parameter) throws Exception{
		HashMap<String,Object> map = new HashMap<String,Object>();
		
		//게시글 현재기준 월별 조회
		List<Map<String, Object>> result = boardService.getBoardBChart(map);
		return result;
 	}
}
