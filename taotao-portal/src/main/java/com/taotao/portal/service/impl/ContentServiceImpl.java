package com.taotao.portal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.portal.service.ContentService;

/**
 * 内容服务
 * <p>Title: ContentServiceImpl</p>
 * <p>Description: </p>
 * @author	黄文俊
 * @date	2017年7月14日下午9:21:26
 * @version 1.0
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	
	@Value("${REST_INDEX_AD_URL}")
	private String REST_INDEX_AD_URL;
	
	@Override
	public String getContentList() {
		// 调用服务层的服务
		String result = HttpClientUtil.doGet(REST_BASE_URL + REST_INDEX_AD_URL);
		// 把字符串转换成taotaoresult
		TaotaoResult taotaoResult = TaotaoResult.format(result);
		// 取出内容列表
		List<TbContent> list = (List<TbContent>) taotaoResult.getData();
		// 创建一个页面需要的pojo
		List<Map> resultList = new ArrayList<>();
		for (TbContent tbContent : list) {
			Map map = new HashMap<>();
			map.put("src", tbContent.getPic());
			map.put("height", 240);
			map.put("width", 670);
			map.put("srcB", tbContent.getPic2());
			map.put("heightB", 240);
			map.put("widthB", 550);
			map.put("href", tbContent.getUrl());
			map.put("alt", tbContent.getSubTitle());
			
			resultList.add(map);
		}
		
		return JsonUtils.objectToJson(resultList);
	}

}
