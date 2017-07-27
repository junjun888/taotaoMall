package com.taotao.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.pojo.TbItemParamItemExample.Criteria;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper tbItemMapper;
	
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	
	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;

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
			String resultStr = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":base");

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
			jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":base", JsonUtils.objectToJson(item));
			jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":base", REDIS_ITEM_EXPIRE);
		} catch (Exception e) {
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}

		return TaotaoResult.ok(item);
	}

	@Override
	public TaotaoResult getItemDesc(long itemId) {
		// 从缓存中获取商品信息 如果查不到 就从数据库中查
		try {
			String resultStr = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + "desc");

			if (!StringUtils.isBlank(resultStr)) {
				// 把 json 转换成 java 对象
				TbItemDesc itemDesc = JsonUtils.jsonToPojo(resultStr, TbItemDesc.class);
				
				return TaotaoResult.ok(itemDesc);
			}
		} catch (Exception e) {
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
		TbItemDesc result = tbItemDescMapper.selectByPrimaryKey(itemId);
		
		// 将商品信息放入缓存中 并且设置有效期
		try {
			jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":desc", JsonUtils.objectToJson(result));
			jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":desc", REDIS_ITEM_EXPIRE);
		} catch (Exception e) {
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
		return TaotaoResult.ok(result);
	}

	@Override
	public TaotaoResult getItemParam(long itemId) {
		// 从缓存中获取商品信息 如果查不到 就从数据库中查
		try {
			String resultStr = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + "param");

			if (!StringUtils.isBlank(resultStr)) {
				// 把 json 转换成 java 对象
				TbItemParamItem itemParamItem = JsonUtils.jsonToPojo(resultStr, TbItemParamItem.class);
				
				return TaotaoResult.ok(itemParamItem);
			}
		} catch (Exception e) {
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
		
		// 根据商品 id 查询规格参数
		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		
		List<TbItemParamItem> list = tbItemParamItemMapper.selectByExampleWithBLOBs(example);
		
		if (list != null && list.size() > 0) {
			TbItemParamItem paramItem = list.get(0);
			
			// 将商品信息放入缓存中 并且设置有效期
			try {
				jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":param", JsonUtils.objectToJson(paramItem));
				jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":param", REDIS_ITEM_EXPIRE);
			} catch (Exception e) {
				return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
			}
			
			return TaotaoResult.ok(paramItem);
		}
		
		
		return TaotaoResult.build(400, "无此商品");
	}

}