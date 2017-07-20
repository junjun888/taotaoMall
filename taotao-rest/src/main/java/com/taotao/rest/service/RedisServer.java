package com.taotao.rest.service;

import com.taotao.common.utils.TaotaoResult;

/**
 * Redis 服务类
 * <p>Title: RedisServer</p>
 * <p>Description: </p>
 * @author	黄文俊
 * @date	2017年7月20日下午10:09:04
 * @version 1.0
 */
public interface RedisServer {
	
	/**
	 * 同步内容缓存
	 * <p>Title: syncContent</p>
	 * <p>Description: </p>
	 * @param contentCid
	 * @return
	 */
	TaotaoResult syncContent(long contentCid);
}