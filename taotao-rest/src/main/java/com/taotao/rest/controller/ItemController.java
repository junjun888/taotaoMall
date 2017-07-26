package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.rest.service.ItemService;

/**
 * 商品信息 Controller
 * <p>Title: ItemController</p>
 * <p>Description: </p>
 * @author	黄文俊
 * @date	2017年7月26日下午10:02:01
 * @version 1.0
 */
@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	private TaotaoResult getItemInfo(@PathVariable Long itemId) {
		return itemService.getItemBaseInfo(itemId);
	}
}