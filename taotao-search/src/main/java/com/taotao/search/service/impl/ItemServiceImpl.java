package com.taotao.search.service.impl;

import java.util.List;

import org.apache.http.util.ExceptionUtils;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.search.mapper.ItemMapper;
import com.taotao.search.pojo.Item;
import com.taotao.search.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private SolrServer solrServer;
	
	@Override
	public TaotaoResult importAllItems() {
		//　查询商品列表
		List<Item> list = itemMapper.getItemList();
		
		try {
			for (Item item : list) {
				// 创建一个 solrInputDocutemnt对象
				SolrInputDocument solrInputDocument = new SolrInputDocument();
				solrInputDocument.setField("id", item.getId());
				solrInputDocument.setField("item_title", item.getTitle());
				solrInputDocument.setField("item_sell_point", item.getSell_point());
				solrInputDocument.setField("item_price", item.getPrice());
				solrInputDocument.setField("item_image", item.getImage());
				solrInputDocument.setField("item_category_name", item.getCategory_name());
				// 写入索引库
				solrServer.add(solrInputDocument);
			}
			
			solrServer.commit();
			
			return TaotaoResult.ok();
		} catch (Exception e) {
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
}