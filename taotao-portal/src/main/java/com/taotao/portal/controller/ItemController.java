package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.pojo.TbItem;
import com.taotao.portal.service.ItemService;

/**
 * 商品详情Controller
 * <p>Title: ItemController</p>
 * <p>Description: </p>
 * @author	黄文俊
 * @date	2017年7月27日下午9:08:38
 * @version 1.0
 */
@Controller
public class ItemController {
	
	@Autowired
	private ItemService ItemService;
	
	@RequestMapping("/item/{itemId}")
	public String showItem(@PathVariable Long itemId, Model model) {
		TbItem item = ItemService.getItemById(itemId);
		model.addAttribute("item", item);
		
		return "item";
	}
}