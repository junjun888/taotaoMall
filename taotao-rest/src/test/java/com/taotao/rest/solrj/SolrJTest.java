package com.taotao.rest.solrj;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * Solr 客户端测试
 * <p>Title: SolrJTest</p>
 * <p>Description: </p>
 * @author	黄文俊
 * @date	2017年7月23日下午8:33:44
 * @version 1.0
 */
public class SolrJTest {

	@Test
	public void addDocument() throws Exception{
		// 创建一个连接、
		SolrServer solrServer = new HttpSolrServer("http://192.168.253.128:8080/solr");
		// 创建一个文档对象
		SolrInputDocument solrInputDocument = new SolrInputDocument();
		// 把文档对象写入数据库
		solrInputDocument.addField("id", "test01");
		solrInputDocument.addField("item_title", "测试商品");
		solrInputDocument.addField("item_price", 12345);
		// 提交
		solrServer.commit();
	}
	
	@Test
	public void deleteDocument() throws Exception {
		// 创建一个连接、
		SolrServer solrServer = new HttpSolrServer("http://192.168.253.128:8080/solr");
		solrServer.deleteById("test01");
		// solrServer.deleteByQuery("*|*");
		solrServer.commit();
	}
	
	@Test
	public void queryDocument() throws Exception {
		// 创建一个连接、
		SolrServer solrServer = new HttpSolrServer("http://192.168.253.128:8080/solr");
		// 创建一个查询对象
		SolrQuery query = new SolrQuery();
		// 设置查询条件
		query.setQuery("*:*");
		query.setStart(20);
		query.setRows(100);
		// 执行查询
		QueryResponse response = solrServer.query(query);
		// 获取查询结果
		SolrDocumentList solrDocumentList = response.getResults();
		
		for (SolrDocument solrDocument : solrDocumentList) {
			System.out.println(solrDocument.get("id"));
			System.out.println(solrDocument.get("item_title"));
			System.out.println(solrDocument.get("item_price"));
		}
	}
}