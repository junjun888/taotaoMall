package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EUTreeNode;


/**
 * 商品类目的接口
 * <p>Title: ItemCatService</p>
 * <p>Description: </p>
 * @author	黄文俊
 * @date	2017年6月23日下午11:16:14
 * @version 1.0
 */
public interface ItemCatService {
	
	/**
	 * 根据商品类目的父节点 id 展示 子类目的列表
	 * <p>Title: getCatList</p>
	 * <p>Description: </p>
	 * @param parentId
	 * @return
	 */
	List<EUTreeNode> getCatList (long parentId) ;
}