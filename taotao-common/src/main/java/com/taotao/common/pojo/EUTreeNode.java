package com.taotao.common.pojo;

/**
 * Easy UI 的 tree node
 * <p>Title: EUTreeNode</p>
 * <p>Description: </p>
 * @author	黄文俊
 * @date	2017年6月23日下午11:12:44
 * @version 1.0
 */
public class EUTreeNode {
	
	private long id;
	private String text;
	private String state;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}