package com.comtec.standard.message.controller;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.comtec.standard.message.service.MessageService;

@Controller
public class MessageController {
	@Resource
	MessageService messageService;
	
	/**
	 * 메시지 작성 이동
	 * @return url
	 */
	@RequestMapping(value="/messageRegist") 
	public String boardRegist() throws Exception{
		return "message/messageRegist";
	}
	
	/**
	 * 메시지 수신
	 * @param parameter HTTP 요청 파라미터
	 * @return key
	 */
	@RequestMapping(value="/insertMessage", method= {RequestMethod.POST}) 
	public @ResponseBody int insertMessage(HttpServletRequest request) throws Exception{
		String[] receiver = request.getParameterValues("receiver")[0].toString().split(",");
		int result = 0;
		
		for(String id : receiver) {
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("receiver", id);
			map.put("content", request.getParameter("content"));
			map.put("msgType", request.getParameter("msgType"));
			map.put("receiverType", request.getParameter("reciType"));
			result += messageService.insertMessage(map);
		}
		
		return result;
 	}
}
