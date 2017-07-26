package com.taotao.rest.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper tbItemMapper;

	@Autowired
	private JedisClient jedisClient;

	@Value("${REDIS_ITEM_KEY}")
	private String REDIS_ITEM_KEY;

	@Value("${REDIS_ITEM_EXPIRE}")
	private String REDIS_ITEM_EXPIRE;

	@Override
	public TaotaoResult getItemBaseInfo(long itemId) {
		// 从缓存中获取商品信息 如果查不到 就从数据库中查
		try {
			String resultStr = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId);

			if (!StringUtils.isBlank(resultStr)) {
				// 把 json 转换成 java 对象
				TbItem item = JsonUtils.jsonToPojo(resultStr, TbItem.class);
				
				return TaotaoResult.ok(item);
			}
		} catch (Exception e) {
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}

		TbItem item = tbItemMapper.selectByPrimaryKey(itemId);

		// 将商品信息放入缓存中 并且设置有效期

		try {
			jedisClient.set(REDIS_ITEM_KEY + ":" + itemId, JsonUtils.objectToJson(item));
			jedisClient.set(REDIS_ITEM_KEY + ":" + itemId, REDIS_ITEM_EXPIRE);
		} catch (Exception e) {
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}

		return TaotaoResult.ok(item);
	}

}