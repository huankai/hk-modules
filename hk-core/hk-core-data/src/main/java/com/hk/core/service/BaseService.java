package com.hk.core.service;

import java.io.Serializable;
import java.util.List;

/**
 * 基本CRUD操作
 * 
 * @author huangkai
 * @date 2017年9月27日下午5:04:48
 * @param <T>
 * @param <PK>
 */
public interface BaseService<T, PK extends Serializable> {

	/**
	 * 保存或更新
	 * 
	 * @param entity
	 * @return
	 */
	<S extends T> S saveOrUpdate(S entity);

	/**
	 * 批量保存或更新
	 * 
	 * @param entities
	 * @return
	 */
	<S extends T> List<S> saveOrUpdate(Iterable<S> entities);

	/**
	 * 
	 * @param entity
	 * @return
	 */
	<S extends T> S saveAndFlush(S entity);

	/**
	 * 
	 * @param id
	 * @return
	 */
	T findOne(PK id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	T getOne(PK id);

	/**
	 * 
	 * @param t
	 * @return
	 */
	<S extends T> S findOne(S t);

	/**
	 * 
	 * @param t
	 * @return
	 */
	<S extends T> List<S> findAll(S t);

	/**
	 * 
	 * @return
	 */
	List<T> findAll();

	/**
	 * 
	 * @param ids
	 * @return
	 */
	List<T> findAll(Iterable<PK> ids);

//	/**
//	 * 
//	 * @param query
//	 * @return
//	 */
//	<S extends T> QueryResultModel<List<S>> findAll(JPAQueryModel<S> query);

	/**
	 * Flushes all pending changes to the database.
	 */
	void flush();

	/**
	 * 
	 * @param id
	 * @return
	 */
	boolean exists(PK id);

	/**
	 * 
	 * @return
	 */
	long count();

	/**
	 * 
	 * @return
	 */
	long count(T t);

	/**
	 * 
	 * @param id
	 */
	void delete(PK id);

	/**
	 * 
	 * @param entity
	 */
	void delete(T entity);

	/**
	 * 
	 * @param entities
	 */
	void delete(Iterable<? extends T> entities);

}
