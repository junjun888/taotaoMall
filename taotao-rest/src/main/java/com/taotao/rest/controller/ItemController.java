package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@RequestMapping("/info/{itemId}")
	@ResponseBody
	private TaotaoResult getItemBaseInfo(@PathVariable Long itemId) {
		return itemService.getItemBaseInfo(itemId);
	}
	
	@RequestMapping("/desc/{itemId}")
	@ResponseBody
	private TaotaoResult getItemDescInfo(@PathVariable Long itemId) {
		return itemService.getItemDesc(itemId);
	}
	
	@RequestMapping("/param/{itemId}")
	@ResponseBody
	private TaotaoResult getItemParamInfo(@PathVariable Long itemId) {
		return itemService.getItemParam(itemId);
	}
}