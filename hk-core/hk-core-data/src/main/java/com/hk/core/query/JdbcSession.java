package com.hk.core.query;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.hk.commons.util.ConverterUtils;
import com.hk.core.dialect.Dialect;

/**
 * 
 * @author huangkai
 * @date 2017年12月11日下午1:30:11
 */
public class JdbcSession {

	private JdbcTemplate jdbcTemplate;

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private Dialect dialect;

	public Dialect getDialect() {
		return dialect;
	}

	public void setDialect(Dialect dialect) {
		this.dialect = dialect;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	// public int update(String sql, Map<String, ?> paramMap) {
	// return namedParameterJdbcTemplate.update(sql, paramMap);
	// }
	//
	// public int update(String sql, Object... args) {
	// return jdbcTemplate.update(sql, args);
	// }

	public <T> List<T> queryForList(Sql sql, Class<T> clazz, Object... args) {
		BeanPropertyRowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(clazz);
		rowMapper.setConversionService(ConverterUtils.DEFAULT_CONVERSIONSERVICE);
		return queryForList(sql, rowMapper, args);
	}

	public <T> List<T> queryForList(Sql sql, RowMapper<T> rowMapper, Object... args) {
		return jdbcTemplate.query(sql.toSqlString(), rowMapper, args);
	}

	public <T> T queryForScalar(Sql sql, Class<T> clazz, Object... args) {
		return jdbcTemplate.queryForObject(sql.toSqlString(), clazz, args);
	}

	// public <T> ListResult<T> queryForList(SelectArguments arguments) {
	// return null;
	// }

	// private class SelectStatement {
	//
	// public StringBuilder selectSql;
	//
	// public StringBuilder countSql;
	//
	// public List<Object> parameters;
	//
	// public SelectStatement(StringBuilder selectSql, StringBuilder countSql,
	// List<Object> parameters) {
	// this.selectSql = selectSql;
	// this.countSql = countSql;
	// this.parameters = parameters;
	// }
	// }

}
