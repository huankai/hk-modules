package com.hk.core.service;

/**
 * 有状态的操作Service
 * 
 * @author huangkai
 * @date 2017年11月30日下午6:01:43
 * @param <T>
 */
public interface StatusService<T> {

	/**
	 * 启用
	 * @param id
	 * @return
	 */
	T enable(String id);

	/**
	 * 禁用
	 * @param id
	 * @return
	 */
	T disable(String id);

}
