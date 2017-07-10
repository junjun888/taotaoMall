package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.utils.JsonUtils;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;

/**
 * 商品分类列表
 * <p>Title: ItemCatController</p>
 * <p>Description: </p>
 * @author	黄文俊
 * @date	2017年7月10日下午10:49:10
 * @version 1.0
 */
@Controller
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;
	
	// 指定了返回结果是 json 字符串 ， 并且字符集是 utf-8
	@RequestMapping(value="/itemcat/list", produces=MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public String getItemCatList(String callback) {
		CatResult catResult = itemCatService.getItemCatList();
		String json = JsonUtils.objectToJson(catResult);
		String result = callback + "(" + json + ");";
		
		return result;
	}
}