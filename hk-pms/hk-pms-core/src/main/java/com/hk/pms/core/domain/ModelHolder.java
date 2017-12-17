package com.hk.pms.core.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.alibaba.fastjson.annotation.JSONField;
import com.hk.core.domain.AbstractAuditable;
import com.hk.core.domain.AbstractUUIDPersistable;

/**
 * @author huangkai
 * @date 2017年10月31日下午1:08:08
 */
@SuppressWarnings("serial")
public class ModelHolder {

	private ModelHolder() {

	}

	@MappedSuperclass
	public static class SysDeptResponBase extends AbstractUUIDPersistable {

		@Column(name = "org_id")
		private String orgId;

		@Column(name = "dept_id")
		private String deptId;

		@Column(name = "user_id")
		private String userId;

		@Column(name = "is_primary")
		private Boolean isPrimary;

		@Column(name = "remark")
		private String remark;

		public String getOrgId() {
			return orgId;
		}

		public void setOrgId(String orgId) {
			this.orgId = orgId;
		}

		public String getDeptId() {
			return deptId;
		}

		public void setDeptId(String deptId) {
			this.deptId = deptId;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public Boolean getIsPrimary() {
			return isPrimary;
		}

		public void setIsPrimary(Boolean isPrimary) {
			this.isPrimary = isPrimary;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

	}

	@MappedSuperclass
	public static class SysUserThirdBase extends AbstractUUIDPersistable {

		@Column(name = "user_id")
		private String userId;

		@Column(name = "user_third_name")
		private String userThirdName;

		@Column(name = "image_url")
		private String imageUrl;

		@Column(name = "account_type")
		private Integer accountType;

		@Column(name = "access_token")
		private String accessToken;

		@Column(name = "refresh_token")
		private String refreshToken;

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getUserThirdName() {
			return userThirdName;
		}

		public void setUserThirdName(String userThirdName) {
			this.userThirdName = userThirdName;
		}

		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}

		public Integer getAccountType() {
			return accountType;
		}

		public void setAccountType(Integer accountType) {
			this.accountType = accountType;
		}

		public String getAccessToken() {
			return accessToken;
		}

		public void setAccessToken(String accessToken) {
			this.accessToken = accessToken;
		}

		public String getRefreshToken() {
			return refreshToken;
		}

		public void setRefreshToken(String refreshToken) {
			this.refreshToken = refreshToken;
		}

	}

	@MappedSuperclass
	public static class SysLoginLogBase extends AbstractUUIDPersistable {

		@Column(name = "user_id")
		private String userId;

		@Column(name = "user_type")
		private Integer userType;

		@Column(name = "user_name")
		private String userName;

		@Column(name = "ip_address")
		private String ipAddress;

		/**
		 * <pre>
		 * false:登陆失败
		 * true：登陆成功
		 * </pre>
		 */
		@Column(name = "log_type")
		private Boolean logType;

		@Column(name = "log_message")
		private String logMessage;

		@Column(name = "create_date")
		private LocalDateTime createDate;

		public LocalDateTime getCreateDate() {
			return createDate;
		}

		public void setCreateDate(LocalDateTime createDate) {
			this.createDate = createDate;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public Integer getUserType() {
			return userType;
		}

		public void setUserType(Integer userType) {
			this.userType = userType;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getIpAddress() {
			return ipAddress;
		}

		public void setIpAddress(String ipAddress) {
			this.ipAddress = ipAddress;
		}

		public Boolean getLogType() {
			return logType;
		}

		public void setLogType(Boolean logType) {
			this.logType = logType;
		}

		public String getLogMessage() {
			return logMessage;
		}

		public void setLogMessage(String logMessage) {
			this.logMessage = logMessage;
		}

	}

	/**
	 * 
	 */
	@MappedSuperclass
	public static class SysOrgDeptBase extends AbstractUUIDPersistable {

		@Column(name = "org_id")
		private String orgId;

		@Column(name = "dept_code")
		private String deptCode;

		@Column(name = "dept_name")
		private String deptName;

		@Column(name = "dept_status")
		private Integer deptStatus;

		public String getOrgId() {
			return orgId;
		}

		public void setOrgId(String orgId) {
			this.orgId = orgId;
		}

		public String getDeptCode() {
			return deptCode;
		}

		public void setDeptCode(String deptCode) {
			this.deptCode = deptCode;
		}

		public String getDeptName() {
			return deptName;
		}

		public void setDeptName(String deptName) {
			this.deptName = deptName;
		}

		public Integer getDeptStatus() {
			return deptStatus;
		}

		public void setDeptStatus(Integer deptStatus) {
			this.deptStatus = deptStatus;
		}

	}

	/**
	 * 机构基本信息
	 */
	@MappedSuperclass
	public static class SysOrgBase extends AbstractUUIDPersistable {

		@Column(name = "parent_id")
		private String parentId;

		@Column(name = "org_code")
		private String orgCode;

		@Column(name = "org_name")
		private String orgName;

		@Column(name = "description")
		private String description;

		@Column(name = "org_status")
		private Integer orgStatus;

		public String getParentId() {
			return parentId;
		}

		public void setParentId(String parentId) {
			this.parentId = parentId;
		}

		public String getOrgCode() {
			return orgCode;
		}

		public void setOrgCode(String orgCode) {
			this.orgCode = orgCode;
		}

		public String getOrgName() {
			return orgName;
		}

		public void setOrgName(String orgName) {
			this.orgName = orgName;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Integer getOrgStatus() {
			return orgStatus;
		}

		public void setOrgStatus(Integer orgStatus) {
			this.orgStatus = orgStatus;
		}

	}

	@MappedSuperclass
	public static class SysChildCodeBase extends AbstractUUIDPersistable {

		@Column(name = "base_code_id")
		private String baseCodeId;

		@Column(name = "child_code")
		private String childCode;

		@Column(name = "chde_name")
		private String codeName;

		@Column(name = "status")
		private Integer status;

		@Column(name = "conditions")
		private String conditions;

		/**
		 * 排序
		 */
		@Column(name = "sort")
		private Integer sort;

		@Column(name = "remark")
		private String remark;

		public String getChildCode() {
			return childCode;
		}

		public void setChildCode(String childCode) {
			this.childCode = childCode;
		}

		public String getBaseCodeId() {
			return baseCodeId;
		}

		public void setBaseCodeId(String baseCodeId) {
			this.baseCodeId = baseCodeId;
		}

		public String getCodeName() {
			return codeName;
		}

		public void setCodeName(String codeName) {
			this.codeName = codeName;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public Integer getSort() {
			return sort;
		}

		public void setSort(Integer sort) {
			this.sort = sort;
		}

		public String getConditions() {
			return conditions;
		}

		public void setConditions(String conditions) {
			this.conditions = conditions;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}
	}

	@MappedSuperclass
	public static class SysBaseCodeBase extends AbstractUUIDPersistable {

		@Column(name = "base_code")
		private String baseCode;

		@Column(name = "code_name")
		private String codeName;

		@Column(name = "remark")
		private String remark;

		public String getBaseCode() {
			return baseCode;
		}

		public void setBaseCode(String baseCode) {
			this.baseCode = baseCode;
		}

		public String getCodeName() {
			return codeName;
		}

		public void setCodeName(String codeName) {
			this.codeName = codeName;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}
	}

	@MappedSuperclass
	public static class UserSettingBase extends AbstractUUIDPersistable {

		@Column(name = "user_id")
		private String userId;

		@Column(name = "key")
		private String key;

		@Column(name = "value")
		private String value;

		@Column(name = "type")
		private String type;

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
	}

	/**
	 * 系统
	 */
	@MappedSuperclass
	public static class SysAppBase extends AbstractUUIDPersistable {

		@Column(name = "app_code")
		private String appCode;

		@Column(name = "app_name")
		private String appName;

		@Column(name = "app_ip")
		private String appIp;

		@Column(name = "app_port")
		private Integer appPort;

		@Column(name = "app_icon")
		private String appIcon;

		@Column(name = "login_url")
		private String loginUrl;

		/**
		 * <pre>
		 *   系统状态
		 *  0：禁用
		 *  1：启用
		 * </pre>
		 */
		@Column(name = "status")
		private Integer status;

		public String getAppName() {
			return appName;
		}

		public void setAppName(String appName) {
			this.appName = appName;
		}

		public String getAppCode() {
			return appCode;
		}

		public void setAppCode(String appCode) {
			this.appCode = appCode;
		}

		public String getAppIp() {
			return appIp;
		}

		public void setAppIp(String appIp) {
			this.appIp = appIp;
		}

		public Integer getAppPort() {
			return appPort;
		}

		public void setAppPort(Integer appPort) {
			this.appPort = appPort;
		}

		public String getAppIcon() {
			return appIcon;
		}

		public void setAppIcon(String appIcon) {
			this.appIcon = appIcon;
		}

		public String getLoginUrl() {
			return loginUrl;
		}

		public void setLoginUrl(String loginUrl) {
			this.loginUrl = loginUrl;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}
	}

	@MappedSuperclass
	public static class RolePermissionBase extends AbstractUUIDPersistable {

		@Column(name = "role_id")
		private String roleId;

		@Column(name = "permission_id")
		private String permissionId;

		public String getRoleId() {
			return roleId;
		}

		public void setRoleId(String roleId) {
			this.roleId = roleId;
		}

		public String getPermissionId() {
			return permissionId;
		}

		public void setPermissionId(String permissionId) {
			this.permissionId = permissionId;
		}

	}

	@MappedSuperclass
	public static class UserRoleBase extends AbstractUUIDPersistable {

		@Column(name = "user_id")
		private String userId;

		@Column(name = "role_id")
		private String roleId;

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getRoleId() {
			return roleId;
		}

		public void setRoleId(String roleId) {
			this.roleId = roleId;
		}

	}

	@MappedSuperclass
	public static class RoleBase extends AbstractUUIDPersistable {

		@Column(name = "app_id")
		private String appId;

		@Column(name = "role_name")
		private String roleName;

		@Column(name = "show_name")
		private String showName;

		public String getAppId() {
			return appId;
		}

		public void setAppId(String appId) {
			this.appId = appId;
		}

		public String getRoleName() {
			return roleName;
		}

		public void setRoleName(String roleName) {
			this.roleName = roleName;
		}

		public String getShowName() {
			return showName;
		}

		public void setShowName(String showName) {
			this.showName = showName;
		}

	}

	@MappedSuperclass
	public static class PermissionBase extends AbstractUUIDPersistable {

		@Column(name = "app_id")
		private String appId;

		@Column(name = "permission")
		private String permission;

		@Column(name = "show_name")
		private String showName;

		public String getAppId() {
			return appId;
		}

		public void setAppId(String appId) {
			this.appId = appId;
		}

		public String getPermission() {
			return permission;
		}

		public void setPermission(String permission) {
			this.permission = permission;
		}

		public String getShowName() {
			return showName;
		}

		public void setShowName(String showName) {
			this.showName = showName;
		}

	}

	@MappedSuperclass
	public static class UserBase extends AbstractAuditable {

		@Column(name = "org_id")
		private String orgId;

		@Column(name = "user_name")
		private String userName;

		@JSONField(serialize = false, deserialize = false)
		@Column(name = "pass_word")
		private String passWord;

		@Column(name = "nick_name")
		private String nickName;

		@Column(name = "aaaa")
		@JSONField(format = "yyyy-MM-dd HH:mm:ss")
		private LocalDateTime aaaa;

		public LocalDateTime getAaaa() {
			return aaaa;
		}

		public void setAaaa(LocalDateTime aaaa) {
			this.aaaa = aaaa;
		}

		@Column(name = "email")
		private String email;

		@Column(name = "phone")
		private String phone;

		@Column(name = "icon_path")
		private String iconPath;

		/**
		 * <pre>
		 *     用户性别：
		 *     1：男
		 *     2：女
		 *     9：未知
		 * </pre>
		 */
		@Column(name = "sex")
		private Integer sex;

		@Column(name = "brithday")
		private LocalDate brithday;

		/**
		 * <pre>
		 *   用户类型：
		 *   1:系统管理员
		 * </pre>
		 */
		@Column(name = "user_type")
		private Integer userType;

		/**
		 *
		 */
		@Column(name = "is_protected")
		private Boolean isProtected;

		/**
		 * <pre>
		 *     用户状态：
		 *     0：禁用
		 *     1：启用
		 * </pre>
		 */
		@Column(name = "user_status")
		private Integer userStatus;

		@Column(name = "remark")
		private String remark;

		public String getOrgId() {
			return orgId;
		}

		public void setOrgId(String orgId) {
			this.orgId = orgId;
		}

		public Boolean getIsProtected() {
			return isProtected;
		}

		public void setIsProtected(Boolean isProtected) {
			this.isProtected = isProtected;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public String getIconPath() {
			return iconPath;
		}

		public void setIconPath(String iconPath) {
			this.iconPath = iconPath;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getPassWord() {
			return passWord;
		}

		public void setPassWord(String passWord) {
			this.passWord = passWord;
		}

		public String getNickName() {
			return nickName;
		}

		public void setNickName(String nickName) {
			this.nickName = nickName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public Integer getSex() {
			return sex;
		}

		public void setSex(Integer sex) {
			this.sex = sex;
		}

		public LocalDate getBrithday() {
			return brithday;
		}

		public void setBrithday(LocalDate brithday) {
			this.brithday = brithday;
		}

		public Integer getUserType() {
			return userType;
		}

		public void setUserType(Integer userType) {
			this.userType = userType;
		}

		public Integer getUserStatus() {
			return userStatus;
		}

		public void setUserStatus(Integer userStatus) {
			this.userStatus = userStatus;
		}

	}
}
