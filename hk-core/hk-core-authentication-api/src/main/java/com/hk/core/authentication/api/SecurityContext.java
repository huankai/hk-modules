package com.hk.core.authentication.api;

/**
 * 此接口可获取当前登陆用户信息、设置与获取session信息
 * 
 * @author huangkai
 * @date 2017年10月23日下午12:50:55
 */
public interface SecurityContext {

	/**
	 * 获取当前登陆的用户
	 * 
	 * @return
	 */
	UserPrincipal getPrincipal();

	/**
	 * 当前用户是否有认证
	 * 
	 * @return
	 */
	boolean isAuthenticated();

	/**
	 * 设置Session属性
	 * 
	 * @param key
	 * @param value
	 */
	void setSessionAttribute(Object key, Object value);

	/**
	 * 设置Session属性
	 * 
	 * @param key
	 * @param value
	 * @param create
	 */
	void setSessionAttribute(Object key, Object value, boolean create);

	/**
	 * 获取Session属性值
	 * 
	 * @param key
	 * @return
	 */
	<T> T getSessionAttribute(Object key);

	/**
	 * 删除session属性值
	 * 
	 * @param key
	 */
	void removeSessionAttribute(Object key);

}
