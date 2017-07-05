package com.taotao.service;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbItemParam;

/**
 * 商品规格类目接口
 * <p>Title: ItemParamService</p>
 * <p>Description: </p>
 * @author	黄文俊
 * @date	2017年7月5日下午9:38:58
 * @version 1.0
 */
public interface ItemParamService {
	
	/**
	 * 根据商品类别查询该列表的规格参数模板
	 * <p>Title: getItemParamByCid</p>
	 * <p>Description: </p>
	 * @param cid
	 * @return
	 */
	TaotaoResult getItemParamByCid(long cid);
	
	/**
	 * 保存商品规格模板
	 * <p>Title: insertItemParam</p>
	 * <p>Description: </p>
	 * @param tbItemParam
	 * @return
	 */
	TaotaoResult insertItemParam(TbItemParam tbItemParam);
}