package com.hk.pms.shiro.realms;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource.Util;
import org.springframework.beans.factory.annotation.Qualifier;

import com.hk.commons.util.CollectionUtils;
import com.hk.commons.util.StringUtils;
import com.hk.core.authentication.api.UserPrincipal;
import com.hk.pms.core.domain.User;
import com.hk.pms.core.service.UserService;

/**
 * 
 * @author huangkai
 * @date 2017年10月23日下午4:14:27
 */
public class JdbcRealm extends AuthorizingRealm {
	
	public static String ADMIN_PERM = "_admin_" + System.currentTimeMillis();

	private UserService userService;
	
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		UserPrincipal userPrincipal = principals.oneByType(UserPrincipal.class);
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		if(userPrincipal.isAdministrator()) {
			authorizationInfo.addStringPermission(ADMIN_PERM);
		} else {
			Map<String, String> rolePermissions = userService.getRoleAndPermissions(userPrincipal.getUserId());
			authorizationInfo.addRoles(rolePermissions.keySet());
			authorizationInfo.addStringPermissions(rolePermissions.values());
		}
		return authorizationInfo;
	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		final String principal = (String) token.getPrincipal();// 用户名
		Optional<User> optional = userService.findUniqueByLoginName(principal);
		validateUser(optional);
		User user = optional.get();
		UserPrincipal userPrincipal = new UserPrincipal(user.getId(), user.getUserName(), user.getNickName(),
				user.getUserType(), user.getPhone(), user.getEmail(), user.getSex());
		//所有密码通过  CryptosUtils.asSha512HashToBase64(source, salt) 加密 ，salt为 用户名
//		@see com.hk.pms.PmsApplication.realm()
		return new SimpleAuthenticationInfo(userPrincipal,user.getPassWord(),Util.bytes(user.getUserName()), getName());
	}
	
	private void validateUser(Optional<User> user) {
		if (!user.isPresent()) {
			throw new UnknownAccountException();
		}
		if (user.get().locked()) {
			throw new LockedAccountException();
		}
	}
	
	@Override
	protected boolean isPermitted(Permission permission, AuthorizationInfo info) {
		Collection<Permission> permissions = getPermissions(info);
		if(CollectionUtils.isNotEmpty(permissions)) {
			for (Permission perm : permissions) {
				if(StringUtils.equals(ADMIN_PERM, perm.toString()) || perm.implies(permission)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void setUserService(@Qualifier("userServiceImpl")UserService userService) {
		this.userService = userService;
	}
}
