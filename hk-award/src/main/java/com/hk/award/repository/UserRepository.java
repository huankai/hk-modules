/**
 * 
 */
package com.hk.award.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.hk.award.domain.User;
import com.hk.award.util.AwardLevel;
import com.hk.commons.util.StringUtils;

/**
 * @author kally
 * @date 2018年1月15日下午1:12:14
 */
@Repository
public class UserRepository {

	/**
	 * 所有用户
	 */
	private static List<User> ALL_USER_LIST = new ArrayList<>();

	/**
	 * 中奖用户
	 */
	private static Map<AwardLevel, List<User>> AWARE_USER_LIST = new HashMap<>();

	/**
	 * 初始化
	 * 
	 * @param userList
	 */
	public void init(List<User> userList) {
		ALL_USER_LIST.clear();
		ALL_USER_LIST.addAll(userList);
		AWARE_USER_LIST.clear();
	}

	public List<User> getAllUserList() {
		return ALL_USER_LIST;
	}

	public Map<AwardLevel, List<User>> getAllAwardUserList() {
		return AWARE_USER_LIST;
	}

	/**
	 * 某个用户已中奖
	 * 
	 * @param phone
	 *            中奖用户手机号
	 * @param awardLevel
	 *            中奖等级
	 * @return
	 */
	public User awardUser(String phone, AwardLevel awardLevel) {
		Optional<User> optional = ALL_USER_LIST.stream().filter(item -> StringUtils.equals(item.getPhone(), phone)).findFirst();
		if (!optional.isPresent()) {
			throw new RuntimeException("不存在的用户：" + phone);
		}
		User user = optional.get();
		ALL_USER_LIST.remove(user);
		List<User> list = AWARE_USER_LIST.get(awardLevel);
		if (null == list) {
			list = new ArrayList<>();
		}
		list.add(user);
		AWARE_USER_LIST.put(awardLevel, list);
		return user;
	}
}
