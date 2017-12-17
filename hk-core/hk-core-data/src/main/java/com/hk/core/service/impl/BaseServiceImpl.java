package com.hk.core.service.impl;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.transaction.annotation.Transactional;

import com.hk.core.authentication.api.SecurityContext;
import com.hk.core.authentication.api.UserPrincipal;
import com.hk.core.repository.BaseRepository;
import com.hk.core.service.BaseService;

/**
 * 基本Service CRUD操作
 * 
 * @author huangkai
 * @date 2017年9月27日下午2:16:24
 * @param <T>
 */
@Transactional(readOnly = true)
public abstract class BaseServiceImpl<T, PK extends Serializable> implements BaseService<T, PK> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected abstract BaseRepository<T, PK> getBaseRepository();

	@Autowired
	protected SecurityContext securityContext;

	/**
	 * 获取当前登陆的用户
	 * 
	 * @return
	 */
	protected UserPrincipal getUserPrincipal() {
		return securityContext.getPrincipal();
	}

	@Override
	@Transactional(readOnly = false)
	public <S extends T> S saveOrUpdate(S entity) {
		return getBaseRepository().save(entity);
	}

	@Override
	@Transactional(readOnly = false)
	public <S extends T> List<S> saveOrUpdate(Iterable<S> entities) {
		return getBaseRepository().save(entities);
	}

	@Override
	@Transactional(readOnly = false)
	public <S extends T> S saveAndFlush(S entity) {
		return getBaseRepository().saveAndFlush(entity);
	}

	@Override
	public final T findOne(PK id) {
		return getBaseRepository().findOne(id);
	}

	@Override
	public <S extends T> S findOne(S t) {
		return getBaseRepository().findOne(getExample(t));
	}

	@Override
	public <S extends T> List<S> findAll(S t) {
		return getBaseRepository().findAll(getExample(t));
	}

	@Override
	public final T getOne(PK id) {
		return getBaseRepository().getOne(id);
	}

	@Override
	@Transactional(readOnly = false)
	public final void flush() {
		getBaseRepository().flush();
	}

	@Override
	public final boolean exists(PK id) {
		return getBaseRepository().exists(id);
	}

	@Override
	public final List<T> findAll() {
		return getBaseRepository().findAll();
	}

	@Override
	public final List<T> findAll(Iterable<PK> ids) {
		return getBaseRepository().findAll(ids);
	}

	/**
	 * @param t
	 * @return
	 */
	protected <S extends T> Example<S> getExample(S t) {
		return Example.of(t);
	}

	// @Override
	// public final <S extends T> QueryResultModel<List<S>> findAll(JPAQueryModel<S>
	// query) {
	// if (query.isPaging()) {
	// Page<S> page = getBaseRepository().findAll(getExample(query.getParams()),
	// new PageRequest(query.getStartRowIndex(), query.getPageSize(),
	// query.toSort()));
	// return new QueryResultModel<>(page.getTotalElements(), page.getContent(),
	// query);
	// } else {
	// List<S> list = getBaseRepository().findAll(getExample(query.getParams()),
	// query.toSort());
	// return new QueryResultModel<List<S>>(list.size(), list, query);
	// }
	// }

	@Override
	public final long count() {
		return getBaseRepository().count();
	}

	@Override
	public long count(T t) {
		return getBaseRepository().count(getExample(t));
	}

	@Override
	@Transactional(readOnly = false)
	public final void delete(PK id) {
		getBaseRepository().delete(id);
	}

	@Override
	@Transactional(readOnly = false)
	public final void delete(T entity) {
		getBaseRepository().delete(entity);
	}

	@Override
	@Transactional(readOnly = false)
	public final void delete(Iterable<? extends T> entities) {
		getBaseRepository().delete(entities);
	}

}
