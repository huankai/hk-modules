/**
 * 
 */
package com.hk.pms.core.service.impl;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hk.commons.util.AssertUtils;
import com.hk.commons.util.EnumDisplayUtils;
import com.hk.commons.util.NumberUtils;
import com.hk.commons.util.StringUtils;
import com.hk.commons.util.encrypt.Encrypt;
import com.hk.core.exception.ServiceException;
import com.hk.core.repository.BaseRepository;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.pms.core.domain.User;
import com.hk.pms.core.repository.UserRepository;
import com.hk.pms.core.service.UserService;

/**
 * @author huangkai
 * @date 2017年12月20日下午2:12:08
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, String> implements UserService {

	public static final String SESSION_PHONE_VERIFICATION_CODE = "PHONE_VERIFICATION_CODE";

	public static final String SESSION_EMAIL_VERIFICATION_CODE = "EMAIL_VERIFICATION_CODE";

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private Encrypt encrypt;

	@Override
	protected BaseRepository<User, String> getBaseRepository() {
		return userRepository;
	}

	@Override
	protected Example<User> getExample(User t) {
		if (null == t) {
			t = new User();
			t.setIsProtected(false);
		}
		return Example.of(t, ExampleMatcher.matching()//
				.withIgnoreNullValues()//
				.withIgnorePaths("passWord")//
				.withMatcher("nickName", GenericPropertyMatchers.contains()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hk.core.service.StatusService#enable(java.lang.String)
	 */
	@Override
	public void enable(String userId) {
		User user = getOne(userId);
		Integer order = EnumDisplayUtils.getDisplayOrder(User.UserStatus.ENABLE);
		if (NumberUtils.nequals(user.getUserStatus(), order)) {
			user.setUserStatus(order);
			saveOrUpdate(user);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hk.core.service.StatusService#disable(java.lang.String)
	 */
	@Override
	public void disable(String id) {
		User user = getOne(id);
		Integer order = EnumDisplayUtils.getDisplayOrder(User.UserStatus.DISABLE);
		if (NumberUtils.nequals(user.getUserStatus(), order)) {
			user.setUserStatus(order);
			saveOrUpdate(user);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hk.pms.core.service.UserService#findUniqueByLoginName(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<User> findUniqueByLoginName(String loginName) {
		return userRepository.findUniqueByLoginName(loginName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hk.pms.core.service.UserService#saveUser(com.hk.pms.core.domain.User)
	 */
	@Override
	public boolean saveUser(User user) {
		boolean exists = existsByLoginName(user.getUserName());
		if (exists) {
			throw new ServiceException("已存在的用户账号");
		}
		user.setPassWord(encrypt.asSha512HashToBase64(user.getPassWord(), user.getUserName()));
		user.setUserStatus(EnumDisplayUtils.getDisplayOrder(User.UserStatus.ENABLE));// 启用状态
		saveOrUpdate(user);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hk.pms.core.service.UserService#existsByLoginName(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public boolean existsByLoginName(String loginName) {
		return userRepository.existsByLoginName(loginName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hk.pms.core.service.UserService#getRoleAndPermissions(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Map<String, String> getRoleAndPermissions(String userId) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hk.pms.core.service.UserService#updatePasswordByUserId(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public boolean updatePasswordByUserId(String userId, String oldPassword, String newPassword) {
		User user = getOne(userId);
		if (!StringUtils.equals(user.getPassWord(), encrypt.asSha512HashToBase64(oldPassword, user.getUserName()))) {
			if (logger.isWarnEnabled()) {
				logger.warn("用户修改密码失败，原密码错误，用户id为 :{}", userId);
			}
			throw new ServiceException("修改密码失败");
		}
		return updatePassword(user, newPassword);
	}

	private boolean updatePassword(User user, String newPassword) {
		AssertUtils.notBlank(newPassword);
		user.setPassWord(encrypt.asSha512HashToBase64(newPassword, user.getUserName()));
		saveOrUpdate(user);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hk.pms.core.service.UserService#updatePasswordByPhone(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public boolean updatePasswordByPhone(String phone, String verificationCode, String newPassword) {
		AssertUtils.notBlank(verificationCode);
		AssertUtils.notBlank(phone);
		String sessionVerificationCode = securityContext.getSessionAttribute(SESSION_PHONE_VERIFICATION_CODE);
		if (!StringUtils.equals(verificationCode, sessionVerificationCode)) {
			if (logger.isWarnEnabled()) {
				logger.warn("用户修改密码失败，验证码错误，用户手机号为 :{}", phone);
			}
			throw new ServiceException("验证码不正确");
		}
		Optional<User> optional = findUniqueByLoginName(phone);
		checkUser(optional);
		return updatePassword(optional.get(), newPassword);
	}

	private void checkUser(Optional<User> optional) {
		if (!optional.isPresent()) {
			throw new ServiceException("用户不存在");
		}
		if (optional.get().locked()) {
			throw new ServiceException("用户已锁定");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hk.pms.core.service.UserService#updatePasswordByEmail(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public boolean updatePasswordByEmail(String email, String verificationCode, String newPassword) {
		AssertUtils.notBlank(email);
		AssertUtils.notBlank(verificationCode);
		String sessionVerificationCode = securityContext.getSessionAttribute(SESSION_EMAIL_VERIFICATION_CODE);
		if (!StringUtils.equals(verificationCode, sessionVerificationCode)) {
			if (logger.isWarnEnabled()) {
				logger.warn("用户修改密码失败，验证码错误，用户邮箱号为 :{}", email);
			}
			throw new ServiceException("验证码不正确");
		}
		Optional<User> optional = findUniqueByLoginName(email);
		checkUser(optional);
		return updatePassword(optional.get(), newPassword);
	}

}
