/**
 * 
 */
package com.hk.core.web.query;

import java.util.List;

import com.hk.core.query.QueryModel;
import com.hk.core.query.jdbc.ListResult;

/**
 * 查询返回结果
 * 
 * @author huangkai
 * @date 2017年12月23日下午3:37:24
 */
public class SimpleQueryResultModel<T> implements QueryResultPageable<T> {

	private int pageIndex;

	private int pageSize;

	private long totalRowCount;

	private List<T> data;

	/**
	 * @param pageIndex
	 * @param pageSize
	 * @param totalCount
	 * @param data
	 */
	public SimpleQueryResultModel(long total, QueryModel queryModel, List<T> data) {
		totalRowCount = total;
		pageSize = queryModel.getPageSize();
		pageIndex = queryModel.getPageIndex();
		this.data = data;
	}

	/**
	 * 
	 * @param queryModel
	 * @param result
	 */
	public SimpleQueryResultModel(QueryModel queryModel, ListResult<T> result) {
		long total = queryModel.getTotalRowCount();
		totalRowCount = total;
		pageSize = queryModel.getPageSize();
		pageIndex = queryModel.getPageIndex();
		this.data = result.getResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hk.core.query.QueryPageable#getPageIndex()
	 */
	@Override
	public int getPageIndex() {
		return pageIndex;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hk.core.query.QueryPageable#getPageSize()
	 */
	@Override
	public int getPageSize() {
		return pageSize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hk.core.query.QueryPageable#getTotalCount()
	 */
	@Override
	public long getTotalRowCount() {
		return totalRowCount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hk.core.query.QueryPageable#getData()
	 */
	@Override
	public List<T> getData() {
		return data;
	}

}
