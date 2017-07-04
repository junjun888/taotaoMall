package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.service.ItemCatService;

/**
 * 商品类目管理 Service
 * <p>Title: ItemCatServiceImpl</p>
 * <p>Description: </p>
 * @author	黄文俊
 * @date	2017年6月23日下午11:18:40
 * @version 1.0
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
	
	@Autowired
	private TbItemCatMapper tbItemCatMapper;

	@Override
	public List<EUTreeNode> getCatList(long parentId) {
		TbItemCatExample example = new TbItemCatExample();
		// 设置查询条件
		Criteria criteria = example.createCriteria();
		// 根据 parentId 查询子节点
		criteria.andParentIdEqualTo(parentId);
		//返回子节点列表
		List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
		// 把列表转换成 treeNode list
		List<EUTreeNode> euTreeNodes = new ArrayList<>();
		
		for (TbItemCat tbItemCat : list) {
			EUTreeNode euTreeNode = new EUTreeNode();
			euTreeNode.setId(tbItemCat.getId());
			euTreeNode.setState(tbItemCat.getIsParent() == true ? "closed":"open");
			euTreeNode.setText(tbItemCat.getName());
			euTreeNodes.add(euTreeNode);
		}
		
		return euTreeNodes;
	}
}