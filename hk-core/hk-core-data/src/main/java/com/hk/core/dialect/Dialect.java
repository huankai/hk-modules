package com.hk.core.dialect;

/**
 * 
 * @author huangkai
 * @date 2017年12月11日下午1:28:11
 */
public interface Dialect {
	
	String getLimitSql(String sql,int offset,int rows);

}
