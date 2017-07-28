package com.taotao.portal.controller;

import javax.print.attribute.standard.Media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.TbItem;
import com.taotao.portal.pojo.ItemInfo;
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
		ItemInfo item = ItemService.getItemById(itemId);
		model.addAttribute("item", item);
		
		return "item";
	}
	
	@RequestMapping(value="/item/desc/{itemId}", produces=MediaType.TEXT_HTML_VALUE + ";charset=utf-8")
	@ResponseBody
	public String getItemDesc(@PathVariable Long itemId) {
		String descStr = ItemService.getItemDescById(itemId);
		
		return descStr;
	}
	
}