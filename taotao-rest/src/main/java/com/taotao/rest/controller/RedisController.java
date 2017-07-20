package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.rest.service.RedisServer;

/**
 * Redis 控制层
 * <p>Title: RedisController</p>
 * <p>Description: </p>
 * @author	黄文俊
 * @date	2017年7月20日下午10:18:04
 * @version 1.0
 */
@Controller
@RequestMapping("/cache/sync")
public class RedisController {
	
	@Autowired
	private RedisServer redisServer;
	
	@RequestMapping("/content/{contentid}")
	@ResponseBody
	public TaotaoResult contentCacheSync(@PathVariable Long contentid){
		TaotaoResult result = redisServer.syncContent(contentid);
		return result;
	}
}