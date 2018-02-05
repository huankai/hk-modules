package com.hk.pms.shiro.realms;

/**
 * Shiro Realm，此项目不再使用shiro
 * @author huangkai
 * @date 2017年10月23日下午4:14:27
 */
@Deprecated
public class JdbcRealm /* extends AuthorizingRealm */{
	
//	public static String ADMIN_PERM = "_admin_" + System.currentTimeMillis();
//
//	private UserService userService;
//	
//	/**
//	 * 授权
//	 */
//	@Override
//	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//		UserPrincipal userPrincipal = principals.oneByType(UserPrincipal.class);
//		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//		if(userPrincipal.isAdministrator()) {
//			authorizationInfo.addStringPermission(ADMIN_PERM);
//		} else {
//			Map<String, String> rolePermissions = userService.getRoleAndPermissions(userPrincipal.getUserId());
//			authorizationInfo.addRoles(rolePermissions.keySet());
//			authorizationInfo.addStringPermissions(rolePermissions.values());
//		}
//		return authorizationInfo;
//	}
//
//	/**
//	 * 认证
//	 */
//	@Override
//	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//		final String principal = (String) token.getPrincipal();// 用户名
//		Optional<User> optional = userService.findUniqueByLoginName(principal);
//		validateUser(optional);
//		User user = optional.get();
//		UserPrincipal userPrincipal = new UserPrincipal(user.getId(), user.getUserName(), user.getNickName(),
//				user.getUserType(), user.getPhone(), user.getEmail(), user.getSex());
//		//所有密码通过  CryptosUtils.asSha512HashToBase64(source, salt) 加密 ，salt为 用户名
////		@see com.hk.pms.PmsApplication.realm()
//		return new SimpleAuthenticationInfo(userPrincipal,user.getPassWord(),Util.bytes(user.getUserName()), getName());
//	}
//	
//	private void validateUser(Optional<User> user) {
//		if (!user.isPresent()) {
//			throw new UnknownAccountException();
//		}
//		if (user.get().locked()) {
//			throw new LockedAccountException();
//		}
//	}
//	
//	@Override
//	protected boolean isPermitted(Permission permission, AuthorizationInfo info) {
//		Collection<Permission> permissions = getPermissions(info);
//		if(CollectionUtils.isNotEmpty(permissions)) {
//			for (Permission perm : permissions) {
//				if(StringUtils.equals(ADMIN_PERM, perm.toString()) || perm.implies(permission)) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}
//	
//	public void setUserService(@Qualifier("userServiceImpl")UserService userService) {
//		this.userService = userService;
//	}
}
