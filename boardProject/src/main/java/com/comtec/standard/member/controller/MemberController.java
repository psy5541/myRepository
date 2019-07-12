package com.comtec.standard.member.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.comtec.standard.member.service.MemberService;
import com.comtec.standard.member.vo.MemberVO;

@Controller
//@MapperScan(basePackages="com.example.demo.member.dao")
public class MemberController {
	//@GetMapping("/join") 
	/*
	 * @Resource(name="memberDao") MemberDAO memberDao;
	 */
	
	@Resource
	MemberService memberService;
	
	/**
	 * 회원가입 이동
	 * @return url
	 */
	@RequestMapping(value="/joinMember") 
	public String joinMember() throws Exception{
		return "joinMember";
	}

	/**
	 * 로그인 화면 이동
	 * @return url
	 */
	@RequestMapping(value="/loginMember") 
	public String loginMember() throws Exception{
		return "login";
	}
	
	@RequestMapping(value="/memberList")
	public @ResponseBody List<MemberVO> memberList() throws Exception{
		return memberService.memberList();
	}
	
	//@RequestMapping(value="/memberList")
	//public @ResponseBody String memberList(@PathVariable String userId) {
	//	MemberVO memberVo = memberMapper.memberList(userId);
	//	return memberVo.getUserId();
	//}
	
	/**
	 * 회원 정보 등록
	 * @param parameter HTTP 요청 파라미터
	 * @return key
	 */
	/*
	 * @RequestMapping(value="/insertMember", method=RequestMethod.GET) public int
	 * insertMember(@RequestParam HashMap<String,String> parameter) throws
	 * Exception{ int key = memberDao.insertMember(parameter);
	 * 
	 * return key; }
	 */
}
