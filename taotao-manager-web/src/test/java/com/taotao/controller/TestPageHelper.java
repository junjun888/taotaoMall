package com.taotao.controller;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;

/**
 * 测试pageHelper
 * <p>Title: TestPageHelper</p>
 * <p>Description: </p>
 * @author	黄文俊
 * @date	2017年6月22日下午10:12:32
 * @version 1.0
 */
public class TestPageHelper {
	
	@Test
	public void testPageHelper() {
		// 创建 Spring容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		// 从Spring 容器中获取Mapper 的代理对象
		TbItemMapper mapper = applicationContext.getBean(TbItemMapper.class);
		// 执行查询并且分页
		TbItemExample example = new TbItemExample();
		PageHelper.startPage(1, 10);
		// 分页处理
		List<TbItem> list = mapper.selectByExample(example);
		// 获取商品列表
		for (TbItem tbItem : list) {
			System.out.println(tbItem.getTitle());
		}
		// 取分页信息
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
		System.out.println("共有" + total);
	}
}