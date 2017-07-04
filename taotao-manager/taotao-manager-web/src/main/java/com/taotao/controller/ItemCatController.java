package com.taotao.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemCatService;

/**
 * 商品分类管理 Controller 
 * <p>Title: ItemCatController</p>
 * <p>Description: </p>
 * @author	黄文俊
 * @date	2017年6月25日下午1:32:25
 * @version 1.0
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> categoryList (@RequestParam(value="id", defaultValue="0") Long parentId) throws Exception{
		List<EUTreeNode> list = itemCatService.getCatList(parentId);
		
		return list;
	}
}