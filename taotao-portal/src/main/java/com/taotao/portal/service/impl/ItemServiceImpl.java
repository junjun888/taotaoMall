package com.taotao.portal.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbItemDesc;
import com.taotao.portal.pojo.ItemInfo;
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
	
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	
	@Value("${ITEM_INFO_URL}")
	private String ITEM_INFO_URL;
	
	@Value("${ITEM_DESC_URL}")
	private String ITEM_DESC_URL;

	@Value("${ITEM_PARAM_URL}")
	private String ITEM_PARAM_URL;
	
	@Override
	public ItemInfo getItemById(Long itemId) {
		try {
			// 调用 rest 服务 查询基本信息
			String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL + itemId);
			if (!StringUtils.isBlank(json)) {
				TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, ItemInfo.class);
				if (taotaoResult.getStatus() == 200) {
					ItemInfo item = (ItemInfo) taotaoResult.getData();
					
					return item;
				}
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	@Override
	public String getItemDescById(Long itemId) {
		try {
			String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_DESC_URL + itemId);
			// 转换成 java 对象
			TaotaoResult taotaoResult = TaotaoResult .formatToPojo(json, TbItemDesc.class);
			
			if (taotaoResult.getStatus() == 200) {
				TbItemDesc itemDesc = (TbItemDesc) taotaoResult.getData();
				
				return itemDesc.getItemDesc();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getItemParam(Long itemId) {
		// TODO
		return null;
	}
	
}