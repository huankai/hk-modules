package com.hk.core.repository.impl;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.hk.core.query.jdbc.JdbcSession;
import com.hk.core.query.jdbc.ListResult;
import com.hk.core.query.jdbc.SelectArguments;

/**
 * 
 * @author huangkai
 *
 */
public abstract class BaseDaoImpl {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private JdbcSession jdbcSession;

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * 
	 * @param arguments
	 * @param retriveRowCount
	 * @return
	 */
	public ListResult<Map<String, Object>> queryForList(SelectArguments arguments, boolean retriveRowCount) {
		return jdbcSession.queryForList(arguments, retriveRowCount);
	}

	/**
	 * 
	 * @param arguments
	 * @param retriveRowCount
	 * @param clazz
	 * @return
	 */
	public <T> ListResult<T> queryForList(SelectArguments arguments, boolean retriveRowCount, Class<T> clazz) {
		return jdbcSession.queryForList(arguments, retriveRowCount, clazz);
	}

	/**
	 * 查询唯一
	 * 
	 * @param arguments
	 * @param clazz
	 * @return
	 */
	public final <T> T queryForObject(SelectArguments arguments, Class<T> clazz) {
		return jdbcSession.queryForObject(arguments, clazz);
	}

}
