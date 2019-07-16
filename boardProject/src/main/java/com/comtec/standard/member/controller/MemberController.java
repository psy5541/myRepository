package com.comtec.standard.member.controller;

import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
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
	 * 회원 정보 등록
	 * @param parameter HTTP 요청 파라미터
	 * @return key
	 */
	
	@RequestMapping(value="/insertMember", method= {RequestMethod.GET, RequestMethod.POST}) 
	@ResponseBody
	//public String insertMember(@RequestBody Map<String, Object> parameter, HttpServletResponse response) throws Exception{
	public String insertMember(HttpServletRequest parameter, Model model) throws Exception{
	//public String insertMember(MemberVO memberVo, Model model) throws Exception{
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("userId", parameter.getParameter("userId"));
		map.put("userNm", parameter.getParameter("userNm"));
		map.put("phoneNo", parameter.getParameter("phoneNo"));
		
		String result = memberService.insertMember(map);
		return result;
 	}
	
	
	@RequestMapping(value = "/memberList", method=RequestMethod.POST)
	public @ResponseBody List<MemberVO> memberList(HttpServletRequest request) throws Exception{
		List<MemberVO> result = memberService.memberList();
		return result;
	}
	
	//@RequestMapping(value="/memberList")
	//public @ResponseBody String memberList(@PathVariable String userId) {
	//	MemberVO memberVo = memberMapper.memberList(userId);
	//	return memberVo.getUserId();
	//}
}
