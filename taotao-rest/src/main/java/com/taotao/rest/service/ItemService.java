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
	
	/**
	 * 获取商品描述信息
	 */
	TaotaoResult getItemDesc(long itemId);
	
	/**
	 * 获取商品规格参数信息
	 * <p>Title: getItemParam</p>
	 * <p>Description: </p>
	 * @param itemId
	 * @return
	 */
	TaotaoResult getItemParam(long itemId);
}