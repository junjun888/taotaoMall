package com.taotao.portal.pojo;

import java.util.List;

/**
 * 查询结果的 pojo
 * <p>Title: SearchResult</p>
 * <p>Description: </p>
 * @author	黄文俊
 * @date	2017年7月25日下午8:36:52
 * @version 1.0
 */
public class SearchResult {
	
	/**
	 * 商品列表
	 */
	private List<Item> itemList;
	
	/**
	 * 总记录数
	 */
	private long recordCont;
	
	/**
	 * 总页数
	 */
	private long pageCount;
	
	/**
	 * 当前页 
	 */
	private long currentPage;

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	public long getRecordCont() {
		return recordCont;
	}

	public void setRecordCont(long recordCont) {
		this.recordCont = recordCont;
	}

	public long getPageCount() {
		return pageCount;
	}

	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}

	public long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}
}