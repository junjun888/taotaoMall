package com.taotao.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.RedisServer;

@Service
public class RedisServiceImpl implements RedisServer{

	@Autowired
	private JedisClient jedisClient;
	
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;
	
	@Override
	public TaotaoResult syncContent(long contentid) {
		try {
			jedisClient.hdel(INDEX_CONTENT_REDIS_KEY, String.valueOf(contentid));
			return TaotaoResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}

}