/**
 * 
 */
package com.hk.award.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.award.domain.User;
import com.hk.award.repository.UserRepository;
import com.hk.award.util.AwardLevel;

/**
 * @author kally
 * @date 2018年1月15日下午1:06:50
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUserList() {
		return userRepository.getAllUserList();
	}

	public Map<AwardLevel, List<User>> getAllAwardUserList() {
		return userRepository.getAllAwardUserList();
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
		return userRepository.awardUser(phone, awardLevel);
	}

}
