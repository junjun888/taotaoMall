package com.taotao.portal.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;

/**
 * 搜索服务service
 * <p>Title: SearchServiceImpl</p>
 * <p>Description: </p>
 * @author	黄文俊
 * @date	2017年7月26日下午8:39:42
 * @version 1.0
 */
@Service
public class SearchServiceImpl implements SearchService {
	
	@Value("${SEARCH_BASE_RUL}")
	private String SEARCH_BASE_RUL;

	@Override
	public SearchResult search(String queryString, int page) {
		// 调用taotao-search的服务
		// 查询参数
		Map<String, String> params = new HashMap<>();
		params.put("q", queryString);
		params.put("page", page + "");
		// 调用服务
		String json = HttpClientUtil.doGet(SEARCH_BASE_RUL, params);
		// 把字符串转成java对象
		TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, SearchResult.class);
		
		if (taotaoResult.getStatus() == 200) {
			SearchResult result = (SearchResult) taotaoResult.getData();
			
			return result;
		}
		
		return null;
	}
	
}
