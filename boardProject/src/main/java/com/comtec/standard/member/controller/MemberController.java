package com.comtec.standard.member.controller;

import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;


import com.comtec.standard.member.service.MemberService;
import com.comtec.standard.member.vo.MemberVO;

@Controller
public class MemberController {
	@Resource
	MemberService memberService;
	
	/**
	 * 회원가입 이동
	 * @return url
	 */
	@RequestMapping(value="/joinMember") 
	public String joinMember() throws Exception{
		return "member/joinMember";
	}

	/**
	 * 로그인 화면 이동
	 * @return url
	 */
	@RequestMapping(value="/loginMember") 
	public String loginMember() throws Exception{
		return "member/login";
	}

	/**
	 * 로그아웃
	 * @return url
	 */
	@RequestMapping(value="/logout") 
	public String logout(HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "member/login";
	}

	/**
	 * 사용자/부서 검색 화면 이동
	 * @return url
	 */
	@RequestMapping(value="/udList") 
	public String udList() throws Exception{
		return "member/udList";
	}

	/**
	 * 메인 화면 이동
	 * @return url
	 */
	@RequestMapping(value="/main") 
	public String main(HttpServletRequest request, Model model) throws Exception{
		HttpSession session = request.getSession();
		Map<String, Object> map = (Map<String, Object>) session.getAttribute("loginInfo");
		model.addAttribute("userId", map.get("userId"));
		model.addAttribute("userNm", map.get("userNm"));
		model.addAttribute("phoneNo", map.get("phoneNo"));

		return "menu/main";
	}

	/**
	 * 차트 메인 화면 이동
	 * @return url
	 */
	@RequestMapping(value="/chartLayout") 
	public String chartLayout(HttpServletRequest request, Model model) throws Exception{
		HttpSession session = request.getSession();
		Map<String, Object> map = (Map<String, Object>) session.getAttribute("loginInfo");
		model.addAttribute("userId", map.get("userId"));
		model.addAttribute("userNm", map.get("userNm"));
		model.addAttribute("phoneNo", map.get("phoneNo"));

		return "menu/chartLayout";
	}

	/**
	 * 로그인 
	 * @return url
	 */
	@RequestMapping(value = "/login", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> login(HttpServletRequest parameter) throws Exception{
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("userId", parameter.getParameter("userId"));
		map.put("pwd", parameter.getParameter("pwd"));

		Map<String, Object> infoMap = memberService.selectMemberInfo(map);
		
		if(infoMap == null) {
			infoMap.put("msg", "FAIL");
		}
		else {
			HttpSession session = parameter.getSession();
			session.setAttribute("loginInfo", infoMap);
			session.setMaxInactiveInterval(60*30);
			infoMap.put("msg", "SUCCESS");
		}
		
		return infoMap;
	}


	/**
	 * 부서 목록 조회 
	 * @return url
	 */
	@RequestMapping(value = "/getDeptList", method=RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getDeptList(HttpServletRequest parameter) throws Exception{
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("userId", parameter.getParameter("userId"));
		map.put("pwd", parameter.getParameter("pwd"));

		return memberService.getDeptList(map);
	}
	
	/**
	 * 회원 정보 등록
	 * @param parameter HTTP 요청 파라미터
	 * @return key
	 */
	@RequestMapping(value="/insertMember", method= {RequestMethod.GET, RequestMethod.POST}) 
	public @ResponseBody int insertMember(HttpServletRequest parameter, Model model) throws Exception{
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("userId", parameter.getParameter("userId"));
		map.put("userNm", parameter.getParameter("userNm"));
		map.put("phoneNo", parameter.getParameter("phoneNo"));
		
		int result = memberService.insertMember(map);
		return result;
 	}
	
	
	@RequestMapping(value = "/memberList", method=RequestMethod.POST)
	public @ResponseBody List<MemberVO> memberList(HttpServletRequest request) throws Exception{
		List<MemberVO> result = memberService.memberList();
		return result;
	}
}
