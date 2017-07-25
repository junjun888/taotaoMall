package com.taotao.search.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.taotao.search.dao.SearchDao;
import com.taotao.search.pojo.Item;
import com.taotao.search.pojo.SearchResult;

/**
 * 商品搜索 Dao
 * <p>Title: SearchDaoImpl</p>
 * <p>Description: </p>
 * @author	黄文俊
 * @date	2017年7月25日下午8:46:00
 * @version 1.0
 */
// 加在 dao层的注解
@Repository
public class SearchDaoImpl implements SearchDao {
	
	@Autowired
	private SolrServer solrServer;// solrServer 是一个接口 要注入的是 httpsolrServer 

	@Override
	public SearchResult search(SolrQuery query) throws Exception {
		// 创建返回值对象
		SearchResult result = new SearchResult();
		// 根据查询条件查询结果
		QueryResponse response = solrServer.query(query);
		SolrDocumentList documentList = response.getResults();
		// 获取查询总数量
		result.setRecordCont(documentList.getNumFound());
		List<Item> items = new ArrayList<>();
		//取高亮显示
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
		
		for (SolrDocument solrDocument : documentList) {
			Item item = new Item();
			item.setId((String)solrDocument.get("id"));
			// 取高亮显示的结果
			List<String> list = highlighting.get(solrDocument.get("id")).get("title");
			String title = "";
			
			if (list != null && list.size() > 0) {
				title = list.get(0);
			} else {
				title = (String) solrDocument.get("item_title");
			}
			
			item.setTitle(title);
			item.setImage((String)solrDocument.get("item_image"));
			item.setPrice((long)solrDocument.get("item_price"));
			item.setSell_point((String)solrDocument.get("item_sell_point"));
			item.setCategory_name((String)solrDocument.get("item_category_name"));
			// 添加的商品列表
			items.add(item);
		}
		
		result.setItemList(items);
		
		return result;
	}
}
