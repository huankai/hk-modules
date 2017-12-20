package com.hk.core.query.jdbc;

import java.util.List;

/**
 * 
 * @author huangkai
 * @date 2017年12月20日下午3:23:10
 * @param <T>
 */
public class RangeCondition<T> implements Condition {

	private String field;

	private T start;

	private T end;

	private boolean includeStart;

	private boolean includeEnd;

	@Override
	public String toSqlString(List<Object> parameters) {
		return null;
	}

	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * @return the start
	 */
	public T getStart() {
		return start;
	}

	/**
	 * @return the end
	 */
	public T getEnd() {
		return end;
	}

	/**
	 * @return the includeStart
	 */
	public boolean isIncludeStart() {
		return includeStart;
	}

	/**
	 * @return the includeEnd
	 */
	public boolean isIncludeEnd() {
		return includeEnd;
	}

}
