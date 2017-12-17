package com.hk.core.query;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;

public final class Sql {

	/**
	 * 分页参数
	 */
	private int pageIndex;

	/**
	 * 分页参数
	 */
	private int pageSize;

	/**
	 * 去重查询
	 */
	private boolean distinct;

	/**
	 * 查询字段
	 */
	private Set<String> fields;

	/**
	 * 查询表名
	 */
	private String form;

	/**
	 * 查询排序
	 */
	private List<Order> orders = Lists.newArrayList();

	public String toSqlString() {
		return null;
	}

	@Override
	public String toString() {
		return toSqlString();
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getStartRowIndex() {
		if(pageIndex <= 0) pageIndex = 1;
		return (pageIndex - 1) * pageSize; 
	}
	
	public boolean isDistinct() {
		return distinct;
	}

	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	public Set<String> getFields() {
		return fields;
	}

	public void setFields(Set<String> fields) {
		this.fields = fields;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
