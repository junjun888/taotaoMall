package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.service.ContentCategoryService;

/**
 * 内容分类管理
 * <p>Title: ContentCategoryController</p>
 * <p>Description: </p>
 * @author	黄文俊
 * @date	2017年7月12日下午9:02:33
 * @version 1.0
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

		@Autowired
		private ContentCategoryService contentCategoryService;
		
		/**
		 * 查询内容分类列表
		 * <p>Title: getContentCatList</p>
		 * <p>Description: </p>
		 * @param parentId
		 * @return
		 */
		@RequestMapping("/list")
		@ResponseBody
		public List<EUTreeNode> getContentCatList(
				@RequestParam(value="id", defaultValue="0") Long parentId) {
			List<EUTreeNode> list = contentCategoryService.getCategoryList(parentId);
			
			return list;
		}
		
		@RequestMapping("/create")
		@ResponseBody
		public TaotaoResult createContentCategory(long parentId, String name) {
			TaotaoResult taotaoResult = contentCategoryService.insertContenCategory(parentId, name);
			return taotaoResult; 
		}
		
}