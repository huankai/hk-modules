package com.hk.core.authentication.security;

public class DefaultUserDetailServiceImpl extends UserDetailServiceImpl {

	@Override
	protected SecurityUserPrincipal loadUserByLoginUsername(String username) {
		SecurityUserPrincipal userPrincipal = new SecurityUserPrincipal("1","admin","系统管理员",0,"13800","13800@xx.com",1);
		return userPrincipal;
	}

}
