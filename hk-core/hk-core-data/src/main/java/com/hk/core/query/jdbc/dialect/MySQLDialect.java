package com.hk.core.query.jdbc.dialect;

public class MySQLDialect implements Dialect {

	@Override
	public String getLimitSql(String sql, int offset, int rows) {
		return String.format("%s LIMIT %d,%d", sql, offset, rows);
	}

}