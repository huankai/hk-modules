package com.hk.core.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.hk.core.query.JdbcSession;
import com.hk.core.query.Sql;

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

	protected <T> void queryForPage(Sql sql, Class<T> clazz, Object... args) {
		jdbcSession.queryForList(sql, clazz, args);
	}

//	protected <T> List<T> queryForList(Sql sql, Class<T> clazz, Object... args) {
//		return jdbcSession.queryForList(sql, clazz, args);
//	}
	
	protected <T> List<T> queryForList(Sql sql, Class<T> clazz, Object... args) {
		return jdbcSession.queryForList(sql, clazz, args);
	}

	protected <T> T queryForObject(Sql sql, Class<T> clazz, Object... args) {
		return jdbcSession.queryForScalar(sql, clazz, args);
	}

}
