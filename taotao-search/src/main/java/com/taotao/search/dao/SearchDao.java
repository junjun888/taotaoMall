package com.taotao.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import com.taotao.search.pojo.SearchResult;

public interface SearchDao {
	
	/**
	 * 根据 SolrQuery 对象 返回一个查询结果
	 * <p>Title: search</p>
	 * <p>Description: </p>
	 * @param query
	 * @return
	 */
	SearchResult search(SolrQuery query) throws Exception;
}