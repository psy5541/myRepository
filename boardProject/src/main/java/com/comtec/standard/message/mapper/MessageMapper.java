package com.comtec.standard.message.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MessageMapper{
	/**
	 * 메시지 등록
	 * @param param 
	 * @return key
	 */
	public int insertMessage(HashMap<String,Object> parameter) throws Exception;
}
