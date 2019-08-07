package com.comtec.standard.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.comtec.standard.system.service.SystemService;

@Controller
public class SystemController {
	@Resource
	SystemService systemService;

	
	/**
	 * 특정 코드 조회
	 * @param parameter HTTP 요청 파라미터
	 * @return list
	 */
	@RequestMapping(value="/getCodeList", method= {RequestMethod.POST}) 
	public @ResponseBody List<Map<String, Object>> getCodeList(HttpServletRequest parameter) throws Exception{
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("CODE_CATEGORY_ID", parameter.getParameter("CODE_CATEGORY_ID"));
		
		//특정 코드 조회
		List<Map<String, Object>> result = systemService.getCodeList(map);
		return result;
 	}
}
