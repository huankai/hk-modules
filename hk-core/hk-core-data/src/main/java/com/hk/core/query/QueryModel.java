package com.hk.core.query;

import java.util.List;

import com.google.common.collect.Lists;

public class QueryModel {

	/**
	 * 查询分页参数
	 */
	private int pageIndex;

	/**
	 * 查询分页参数
	 */
	private int pageSize;

	/**
	 * 分页时，不需要再count，所以用该属性来存上一次count的数字
	 */
	private int totalRowCount;

	/**
	 * 查询排序
	 */
	private List<Order> orders = Lists.newArrayList();

	/**
	 * @return the pageIndex
	 */
	public int getPageIndex() {
		return pageIndex;
	}

	/**
	 * @param pageIndex
	 *            the pageIndex to set
	 */
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the orders
	 */
	public List<Order> getOrders() {
		return orders;
	}

	/**
	 * @param orders
	 *            the orders to set
	 */
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	/**
	 * 分页时，不需要再count，所以用该属性来存上一次count的数字
	 * 
	 * @return the totalRowCount
	 */
	public int getTotalRowCount() {
		return totalRowCount;
	}

	/**
	 * 分页时，不需要再count，所以用该属性来存上一次count的数字
	 * 
	 * @param totalRowCount
	 *            the totalRowCount to set
	 */
	public void setTotalRowCount(int totalRowCount) {
		this.totalRowCount = totalRowCount;
	}

}
