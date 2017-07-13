package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;

/**
 * 内容管理
 * <p>Title: ContentController</p>
 * <p>Description: </p>
 * @author	黄文俊
 * @date	2017年7月13日下午8:41:30
 * @version 1.0
 */
@Controller
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult insertContent(TbContent tbContent) {
		TaotaoResult result = contentService.insertContent(tbContent);
		
		return result;
	}
}