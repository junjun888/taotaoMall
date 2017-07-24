package com.taotao.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.search.pojo.Item;
import com.taotao.search.service.ItemService;

/**
 * 索引库维护
 * <p>Title: ItemController</p>
 * <p>Description: </p>
 * @author	黄文俊
 * @date	2017年7月24日下午9:27:48
 * @version 1.0
 */
@Controller
@RequestMapping("/manager")
public class ItemController {
	
	@Autowired
	private ItemService ItemService;
	
	/**
	 * 导入商品数据到索引库
	 */
	@RequestMapping("/importAll")
	@ResponseBody
	public TaotaoResult importAllItems() {
		TaotaoResult taotaoResult = ItemService.importAllItems();
		
		return taotaoResult;
	}
}