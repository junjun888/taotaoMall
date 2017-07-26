package com.taotao.rest.service;

import com.taotao.common.utils.TaotaoResult;

public interface ItemService {
	
	/**
	 * 获取商品的基本信息
	 * <p>Title: getItemBaseInfo</p>
	 * <p>Description: </p>
	 * @param itemId
	 * @return
	 */
	TaotaoResult getItemBaseInfo(long itemId);
}