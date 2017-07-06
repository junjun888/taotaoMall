package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.pojo.TbItemParamItemExample.Criteria;
import com.taotao.service.ItemParamItemService;
import com.taotao.service.ItemParamService;

/**
 * 展示商品规格参数
 * <p>Title: ItemParamItemServiceImpl</p>
 * <p>Description: </p>
 * @author	黄文俊
 * @date	2017年7月6日下午9:36:11
 * @version 1.0
 */
@Service
public class ItemParamItemServiceImpl implements ItemParamItemService{
	
	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;

	@Override
	public String getItemParamByItemId(Long itemId) {
		// 根据商品的id 查询商品的规格参数
		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andCreatedEqualTo(itemId);
		// 执行查询
		List<TbItemParamItem> list = tbItemParamItemMapper.selectByExampleWithBLOBs(example);
		
		if (list == null || list.size() == 0) {
			return "";
		}
		
		// 取规格参数信息
		TbItemParamItem itemParamItem = list.get(0);
		String paramData = itemParamItem.getParamData();
		// 生成 html
		// TODO l
		
		return null;
	}
	
	
}
