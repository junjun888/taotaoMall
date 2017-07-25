package com.taotao.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.search.dao.SearchDao;
import com.taotao.search.pojo.SearchResult;
import com.taotao.search.service.SearchService;

/**
 * 搜索 Service
 * <p>Title: SearchServiceImpl</p>
 * <p>Description: </p>
 * @author	黄文俊
 * @date	2017年7月25日下午9:23:22
 * @version 1.0
 */
@Service
public class SearchServiceImpl implements  SearchService {

	@Autowired
	private SearchDao searchDao;
	
	@Override
	public SearchResult search(String queryString, int page, int row) throws Exception {
		// 创建查询对象
		SolrQuery query = new SolrQuery();
		// 设置查询条件 
		query.setQuery(queryString);
		// 设置分页
		query.setStart((page - 1) * row);
		// 设置默认搜索域
		query.set("df", "item_keywords");
		// 设置高亮显示
		query.setHighlight(true);
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<em style=\"cloler:red\">");
		query.setHighlightSimplePost("</em>");
		// 执行查询
		SearchResult searchResult = searchDao.search(query);
		// 计算查询结果总页数
		long recordCount = searchResult.getRecordCont();
		long pageCount = recordCount / row;
		
		if (recordCount % row > 0) {
			pageCount++;
		}
		
		searchResult.setPageCount(pageCount);
		searchResult.setCurrentPage(page);
		
		return searchResult;
	}
}
