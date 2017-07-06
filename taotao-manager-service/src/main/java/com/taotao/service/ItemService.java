package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {
	
	/**
	 * 根据商品id 获取商品
	 * <p>Title: getItemById</p>
	 * <p>Description: </p>
	 * @param itemId
	 * @return
	 */
	TbItem getItemById (long itemId);
	
	/**
	 * 分页查询 商品列表 
	 * <p>Title: getItemList</p>
	 * <p>Description: </p>
	 * @param page 第几页
	 * @param rows 多少条记录
	 * @return Easy UI 的 dataGrid 对象
	 */
	EUDataGridResult getItemList(int page, int rows);
	
	/**
	 * 新增商品
	 * <p>Title: createItem</p>
	 * <p>Description: </p>
	 * @param item
	 * @return
	 */
	TaotaoResult createItem(TbItem item, String desc, String itemParam) throws Exception;
}
