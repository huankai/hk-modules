package com.hk.core.query.jdbc;

import java.util.List;

public class ListResult<T> {

	private long rowCount;

	private List<T> result;

	/**
	 * @param rowCount
	 * @param result
	 */
	public ListResult(long rowCount, List<T> result) {
		this.rowCount = rowCount;
		this.result = result;
	}

	/**
	 * @return the rowCount
	 */
	public long getRowCount() {
		return rowCount;
	}

	/**
	 * @return the result
	 */
	public List<T> getResult() {
		return result;
	}

}
