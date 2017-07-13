package com.taotao.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.rest.service.ContentService;

/**
 * 商品内容管理
 * <p>Title: ContentServiceImpl</p>
 * <p>Description: </p>
 * @author	黄文俊
 * @date	2017年7月13日下午9:22:46
 * @version 1.0
 */
@Service
public class ContentServiceImpl implements ContentService {
	
	@Autowired
	private TbContentMapper tbContentMapper;

	@Override
	public List<TbContent> getContentList(long contentId) {
		// 根据内容分类 id 查询 content list
		TbContentExample tbContentExample = new TbContentExample();
		Criteria criteria = tbContentExample.createCriteria();
		criteria.andCategoryIdEqualTo(contentId);
		
		List<TbContent> list = tbContentMapper.selectByExample(tbContentExample);
		
		return list;
	}
}