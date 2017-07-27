package com.taotao.portal.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.portal.service.ItemService;

/**
 * 商品信息管理Service
 * <p>Title: ItemServiceImpl</p>
 * <p>Description: </p>
 * @author	黄文俊
 * @date	2017年7月27日下午8:57:07
 * @version 1.0
 */
@Service
public class ItemServiceImpl implements ItemService {
	
	@Value("{REST_BASE_URL}")
	private String REST_BASE_URL;
	
	@Value("{ITEM_INFO_URL}")
	private String ITEM_INFO_URL;

	@Override
	public TbItem getItemById(Long itemId) {
		try {
			// 调用 rest 服务 查询基本信息
			String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL + itemId);
			if (!StringUtils.isBlank(json)) {
				TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItem.class);
				if (taotaoResult.getStatus() == 200) {
					TbItem item = (TbItem) taotaoResult.getData();
					
					return item;
				}
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}
	
}