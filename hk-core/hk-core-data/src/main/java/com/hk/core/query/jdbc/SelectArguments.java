package com.hk.core.query.jdbc;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hk.commons.util.ArrayUtils;
import com.hk.core.query.Order;

public final class SelectArguments {

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
	private String from;

	/**
	 * Group by
	 */
	private Set<String> groupBys;

	/**
	 * 查询排序
	 */
	private List<Order> orders = Lists.newArrayList();

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
		if (pageIndex <= 0) {
			pageIndex = 1;
		}
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

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	/**
	 * @return the groupBys
	 */
	public Set<String> getGroupBys() {
		return groupBys;
	}

	/**
	 * @param groupBys
	 *            the groupBys to set
	 */
	public void setGroupBys(Set<String> groupBys) {
		this.groupBys = groupBys;
	}

	/**
	 * @param groupBys
	 *            the groupBys to set
	 */
	public void setGroupBys(String... groupBys) {
		if (ArrayUtils.isNotEmpty(groupBys)) {
			this.groupBys = Sets.newHashSet(groupBys);
		}
	}

	/**
	 * @param from
	 *            the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

}
