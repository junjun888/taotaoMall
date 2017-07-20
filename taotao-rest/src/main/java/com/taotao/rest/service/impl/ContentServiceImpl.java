package com.taotao.rest.service.impl;

import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.druid.support.logging.Log;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ContentService;

/**
 * 商品内容管理
 * <p>
 * Title: ContentServiceImpl
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author 黄文俊
 * @date 2017年7月13日下午9:22:46
 * @version 1.0
 */
@Service
public class ContentServiceImpl implements ContentService {
	
	@Autowired
	private TbContentMapper tbContentMapper;
	
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;

	@Autowired
	private JedisClient JedisClient;

	@Override
	public List<TbContent> getContentList(long contentId) {
		// 从缓存中取出内容
		try {
			String result = JedisClient.hget(INDEX_CONTENT_REDIS_KEY,String.valueOf(contentId));
			
			if (!StringUtils.isBlank(result)) {
				List<TbContent> resultList = JsonUtils.jsonToList(result, TbContent.class);
				
				return resultList;
			}
		} catch (Exception e) {
			// TODO 输出错误信息
			e.printStackTrace();
		}

		// 根据内容分类 id 查询 content list
		TbContentExample tbContentExample = new TbContentExample();
		Criteria criteria = tbContentExample.createCriteria();
		criteria.andCategoryIdEqualTo(contentId);

		List<TbContent> list = tbContentMapper.selectByExample(tbContentExample);

		// 向缓存中添加内容
		try {
			// 把list 转换成字符串
			String cacheStr = JsonUtils.objectToJson(list);
			JedisClient.hset(INDEX_CONTENT_REDIS_KEY, String.valueOf(contentId), cacheStr);
		} catch (Exception e) {
			// TODO 输出错误信息
			e.printStackTrace();
		}

		return list;
	}
}