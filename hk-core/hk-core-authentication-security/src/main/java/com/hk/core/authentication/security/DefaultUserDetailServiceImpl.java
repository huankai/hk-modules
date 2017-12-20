package com.hk.core.authentication.security;

/**
 * 
 * @author huangkai
 * @date 2017年12月19日下午12:49:09
 */
public class DefaultUserDetailServiceImpl extends UserDetailServiceImpl {

	@Override
	protected SecurityUserPrincipal loadUserByLoginUsername(String username) {
		SecurityUserPrincipal userPrincipal = new SecurityUserPrincipal("1", "admin", "admin", "系统管理员", 0, "13800",
				"13800@xx.com", 1);
		return userPrincipal;
	}

}
