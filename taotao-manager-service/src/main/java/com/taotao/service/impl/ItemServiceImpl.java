package com.taotao.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemService;

/**
 * 商品管理Service
 * <p>Title: ItemServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @author	黄文俊
 * @date	2017年6月21日下午8:57:28
 * @version 1.0
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	
	@Override
	public TbItem getItemById(long itemId) {
		//TbItem item = itemMapper.selectByPrimaryKey(itemId);
		
		TbItemExample example = new TbItemExample();
		// 添加查询条件
		TbItemExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> list = itemMapper.selectByExample(example);
		
		if (list != null && list.size() > 0) {
			TbItem item = list.get(0);
			return item;
		}
		
		return null;
	}

	/**
	 * 分页查询 商品列表 
	 * <p>Title: getItemList</p>
	 * <p>Description: </p>
	 * @param page
	 * @param rows
	 * @return
	 * @see com.taotao.service.ItemService#getItemList(long, long)
	 */
	@Override
	public EUDataGridResult getItemList(int page, int rows) {
		TbItemExample example = new TbItemExample();
		// 分页处理
		PageHelper.startPage(page, rows);
		List<TbItem> list = itemMapper.selectByExample(example);
		// 根据 list 创建 pageInfo 对象
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		result.setTotal(pageInfo.getTotal());
		
		return result;
	}

	/**
	 * 新增商品
	 * <p>Title: createItem</p>
	 * <p>Description: </p>
	 * @param item
	 * @return
	 * @throws Exception 
	 * @see com.taotao.service.ItemService#createItem(com.taotao.pojo.TbItem)
	 */
	@Override
	public TaotaoResult createItem(TbItem item, String desc, String itemParam) throws Exception {
		// item 补全
		// 生产商品 ID
		Long itemId = IDUtils.genItemId();
		item.setId(itemId);
		// 1 正常，2 下架，3 删除
		item.setStatus((byte)1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		// 插入到数据库
		itemMapper.insert(item);
		// 添加商品描述信息
		TaotaoResult result = insertItemDesc(itemId, desc);
		
		if (result.getStatus() != 200) {
			throw new Exception();
		}
		
		result = insertItemParamItem(itemId, itemParam);
		
		if (result.getStatus() != 200) {
			throw new Exception();
		}
		
		return TaotaoResult.ok();
	}
	
	/**
	 * 添加商品描述
	 * <p>Title: insertItemDesc</p>
	 * <p>Description: </p>
	 * @param desc
	 * @return
	 */
	private TaotaoResult insertItemDesc(Long itemId,String desc) {
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDescMapper.insert(itemDesc);
		
		return TaotaoResult.ok();
	}
	
	/**
	 * 添加规格参数
	 * <p>Title: insertItemParamItem</p>
	 * <p>Description: </p>
	 * @return
	 */
	private TaotaoResult insertItemParamItem(Long itemId, String itemParam) {
		// 创建一个PoJo
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setCreated(new Date());
		itemParamItem.setUpdated(new Date());
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(itemParam);
		
		itemParamItemMapper.insert(itemParamItem);
		
		return TaotaoResult.ok();
	}
}
