package com.taotao.search.service;

import com.taotao.common.utils.TaotaoResult;

public interface ItemService {
	
	/**
	 * 将所有的商品信息写入索引库
	 * <p>Title: importAllItems</p>
	 * <p>Description: </p>
	 * @return
	 */
	TaotaoResult importAllItems();
}