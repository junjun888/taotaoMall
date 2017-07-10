package com.taotao.rest.pojo;

import java.util.List;

/**
 * 商品类别的返回结果
 * <p>
 * Title: CatResult
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author 黄文俊
 * @date 2017年7月10日下午10:21:38
 * @version 1.0
 */
public class CatResult {

	/**
	 * 返回的数据集
	 */
	private List<?> data;

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}
}