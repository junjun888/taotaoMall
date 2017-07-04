package com.taotao.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转
 * <p>Title: PageController</p>
 * <p>Description: </p>
 * @author	黄文俊
 * @date	2017年6月22日下午8:53:31
 * @version 1.0
 */
@Controller
public class PageController {

	/**
	 * <p>Title: 打开首页</p>
	 * <p>Description: </p>
	 * @return
	 */
	@RequestMapping("/")
	public String showIndex() {
		return "index";
	}
	
	/**
	 * 展示其他页面
	 * <p>Title: showPage</p>
	 * <p>Description: </p>
	 * @param page
	 * @return
	 */
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page) {
		return page;
	}
}