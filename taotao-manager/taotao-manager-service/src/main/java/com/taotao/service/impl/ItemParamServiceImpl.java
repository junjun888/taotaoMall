package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.service.ItemParamService;

/**
 * 商品的规格参数模板
 * <p>Title: ItemParamServiceImpl</p>
 * <p>Description: </p>
 * @author	黄文俊
 * @date	2017年7月5日下午9:41:03
 * @version 1.0
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {
	
	@Autowired
	private TbItemParamMapper itemParamMapper;
	
	@Override
	public TaotaoResult getItemParamByCid(long cid) {
		TbItemParamExample tbItemParamExample = new TbItemParamExample();
		Criteria criteria = tbItemParamExample.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = itemParamMapper.selectByExample(tbItemParamExample);
		// 判断是否查询到结果
		if (list.size() > 0) {
			return TaotaoResult.ok(list.get(0));
		}
		
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult insertItemParam(TbItemParam tbItemParam) {
		// 补全 pojo
		tbItemParam.setCreated(new Date());
		tbItemParam.setUpdated(new Date());
		// 插入到规格参数模板
		itemParamMapper.insert(tbItemParam);
		return TaotaoResult.ok();
	}

}
