package com.taotao.service.impl;

import java.util.Date;

import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;

/**
 * 内容服务
 * <p>Title: ContentServiceImpl</p>
 * <p>Description: </p>
 * @author	黄文俊
 * @date	2017年7月13日下午8:38:09
 * @version 1.0
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	
	@Value("${REST_CONTENT_SYNC_URL}")
	private String REST_CONTENT_SYNC_URL;
	
	/**
	 * 插入内容
	 * <p>Title: insertContent</p>
	 * <p>Description: </p>
	 * @param tbContent
	 * @return
	 * @see com.taotao.service.ContentService#insertContent(com.taotao.pojo.TbContent)
	 */
	@Override
	public TaotaoResult insertContent(TbContent tbContent) {
		// 补全 pojo
		tbContent.setCreated(new Date());
		tbContent.setUpdated(new Date());
		contentMapper.insert(tbContent);
		
		try {
			HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + tbContent.getId()); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 添加缓存同步逻辑
		
		return TaotaoResult.ok();
	}

}
