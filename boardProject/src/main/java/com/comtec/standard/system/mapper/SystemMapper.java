package com.comtec.standard.system.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SystemMapper{
	
	/**
	 * 특정 코드 조회
	 * @param param 
	 * @return list
	 */
	public List<Map<String, Object>> getCodeList(HashMap<String,Object> parameter) throws Exception;
}
