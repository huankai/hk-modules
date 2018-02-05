/**
 * 
 */
package org.hk.pms.api;

import org.hk.pms.domain.User;

/**
 * @author kally
 * @date 2018年2月5日下午3:09:05
 */
public interface FcadeUserService {

	/**
	 * 根据登陆名查询用户
	 * 
	 * @param loginName
	 *            用户登录名
	 * @return
	 */
	User findByUserName(String loginName);

	/**
	 * 是否存在用户
	 * 
	 * @param loginName
	 *            用户登录名
	 * @return
	 */
	boolean existsByUserName(String loginName);

	/**
	 * 保存或更新用户信息
	 * 
	 * @param user
	 *            用户
	 * @return 保存或更新后的用户
	 */
	User saveOrUpdateUser(User user);

	/**
	 * 批量保存或更新用户信息
	 * 
	 * @param userList
	 *            用户
	 * @return 保存或更新后的用户
	 */
	Iterable<User> saveOrUpdateUserList(Iterable<User> userList);

	/**
	 * 删除用户
	 * 
	 * @param userIds
	 *            用户id
	 * @return
	 */
	boolean deleteByUserIds(String... userIds);

}
