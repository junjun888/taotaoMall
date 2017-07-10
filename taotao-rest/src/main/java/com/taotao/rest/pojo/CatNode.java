package com.taotao.rest.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 商品分类节点
 * <p>
 * Title: CatNode
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author 黄文俊
 * @date 2017年7月10日下午10:16:35
 * @version 1.0
 */
public class CatNode {

	/**
	 * 类别名
	 */
	// 表示转换成 json 后默认的 key 的名字
	@JsonProperty("n")
	private String name;

	/**
	 * 地址
	 */
	@JsonProperty("u")
	private String url;

	/**
	 * 类目
	 */
	@JsonProperty("i")
	private List<?> item;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<?> getItem() {
		return item;
	}

	public void setItem(List<?> item) {
		this.item = item;
	}
}