package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.fabric.xmlrpc.base.Data;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.service.ContentCategoryService;

/**
 * 内容分类管理
 * <p>Title: ContentCategoryImpl</p>
 * <p>Description: </p>
 * @author	黄文俊
 * @date	2017年7月12日下午8:52:31
 * @version 1.0
 */
@Service
public class ContentCategoryImpl implements ContentCategoryService {
	
	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;

	@Override
	public List<EUTreeNode> getCategoryList(long parentId) {
		//根据 parentId 查询节点列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		// 执行查询
		List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
		List<EUTreeNode> resultList = new ArrayList<>();
		
		for (TbContentCategory tbContentCategory : list) {
			EUTreeNode euTreeNode = new EUTreeNode();
			euTreeNode.setId(tbContentCategory.getId());
			euTreeNode.setText(tbContentCategory.getName());
			euTreeNode.setState(tbContentCategory.getIsParent()?"closed":"open");
			
			resultList.add(euTreeNode);
		}
		
		return resultList;
	}

	/**
	 * 插入内容类别
	 * <p>Title: insertContenCategory</p>
	 * <p>Description: </p>
	 * @param parentId
	 * @param name
	 * @return
	 * @see com.taotao.service.ContentCategoryService#insertContenCategory(long, java.lang.String)
	 */
	@Override
	public TaotaoResult insertContenCategory(long parentId, String name) {
		// 创建一个 pojo
		TbContentCategory tbContentCategory = new TbContentCategory();
		tbContentCategory.setName(name);
		tbContentCategory.setIsParent(false);
		// 状态 1：正常  0： 删除
		tbContentCategory.setStatus(1);
		tbContentCategory.setParentId(parentId);
		tbContentCategory.setSortOrder(1);
		tbContentCategory.setCreated(new Date());
		tbContentCategory.setCreated(new Date());
		// 添加记录到数据库中
		tbContentCategoryMapper.insert(tbContentCategory);
		// 查看父节点的 isParent 列是否为true，如果不是改为true
		TbContentCategory contentCategory = tbContentCategoryMapper.selectByPrimaryKey(parentId);
		
		if (!contentCategory.getIsParent()) {
			contentCategory.setIsParent(true);
			tbContentCategoryMapper.updateByPrimaryKey(contentCategory);
		}
		
		// 返回结果
		
		
		return TaotaoResult.ok(tbContentCategory);
	}

}
