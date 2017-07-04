package com.taotao.common.pojo;

import java.util.List;

/**
 * Easy UI dataGrad pojo
 * <p>Title: EUDataGridResult</p>
 * <p>Description: </p>
 * @author	黄文俊
 * @date	2017年6月23日下午8:55:45
 * @version 1.0
 */
public class EUDataGridResult {

	private long total;
	private List<?> rows;
	
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
}