package com.hk.pms.core.domain;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.alibaba.fastjson.annotation.JSONField;
import com.hk.core.domain.AbstractAuditable;
import com.hk.core.domain.AbstractUUIDPersistable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author huangkai
 * @date 2017年10月31日下午1:08:08
 */
@SuppressWarnings("serial")
public class ModelHolder {

	private ModelHolder() {

	}

	@Data
	@EqualsAndHashCode(callSuper = false)
	@MappedSuperclass
	public static class CityBase extends AbstractUUIDPersistable {

		/**
		 * 行政代码
		 */
		@Column(name = "code")
		private String code;

		/**
		 * 全称
		 */
		@Column(name = "full_name")
		private String fullName;

		/**
		 * 简称
		 */
		@Column(name = "short_name")
		private String shortName;

		/**
		 * 英文名
		 */
		@Column(name = "english_name")
		private String englishName;

		/**
		 * 邮编
		 */
		@Column(name = "post_office")
		private String postOffice;

		/**
		 * 上级
		 */
		@ManyToOne(fetch = FetchType.LAZY)
		private City parent;

		/**
		 * 子级
		 */
		@OneToMany(fetch = FetchType.LAZY)
		@JoinColumn(name = "parent_id", referencedColumnName = "id")
		private Set<City> childs;

	}

	@Data
	@EqualsAndHashCode(callSuper = false)
	@MappedSuperclass
	public static class SysDeptResponBase extends AbstractUUIDPersistable {

		@ManyToOne(optional = false)
		private SysOrgDept dept;

		@Column(name = "user_id")
		private String userId;

		@Column(name = "is_primary")
		private Boolean isPrimary;

		@Column(name = "remark")
		private String remark;

	}

	@Data
	@EqualsAndHashCode(callSuper = false)
	@MappedSuperclass
	public static class SysUserThirdBase extends AbstractUUIDPersistable {

		@OneToOne
		private User user;

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

	}

	@Data
	@EqualsAndHashCode(callSuper = false)
	@MappedSuperclass
	public static class SysLoginLogBase extends AbstractUUIDPersistable {

		@ManyToOne(optional = false)
		private User user;

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

	}

	/**
	 * 
	 */
	@Data
	@EqualsAndHashCode(callSuper = false)
	@MappedSuperclass
	public static class SysOrgDeptBase extends AbstractUUIDPersistable {

		@ManyToOne(optional = false)
		private SysOrg sysOrg;

		@Column(name = "dept_code")
		private String deptCode;

		@Column(name = "dept_name")
		private String deptName;

		@Column(name = "dept_status")
		private Integer deptStatus;

	}

	/**
	 * 机构基本信息
	 */
	@Data
	@EqualsAndHashCode(callSuper = false)
	@MappedSuperclass
	public static class SysOrgBase extends AbstractUUIDPersistable {

		/**
		 * 上级
		 */
		@ManyToOne(optional = false)
		private SysOrg parent;

		/**
		 * 子级
		 */
		@OneToMany(fetch = FetchType.LAZY)
		private Set<SysOrg> childs;

		@Column(name = "org_code")
		private String orgCode;

		@Column(name = "org_name")
		private String orgName;

		@Column(name = "description")
		private String description;

		@Column(name = "org_status")
		private Integer orgStatus;

	}

	@Data
	@EqualsAndHashCode(callSuper = false)
	@MappedSuperclass
	public static class SysChildCodeBase extends AbstractUUIDPersistable {

		@ManyToOne(optional = false)
		private SysBaseCode sysBaseCode;

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

	}

	@Data
	@EqualsAndHashCode(callSuper = false)
	@MappedSuperclass
	public static class SysBaseCodeBase extends AbstractUUIDPersistable {

		/**
		 *
		 */
		@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
		@JoinColumn(name = "base_code_id", referencedColumnName = "id")
		private Set<SysChildCode> childCodes;

		@Column(name = "code_name")
		private String codeName;

		@Column(name = "remark")
		private String remark;

	}

	@Data
	@EqualsAndHashCode(callSuper = false)
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

	}

	/**
	 * 系统
	 */
	@Data
	@EqualsAndHashCode(callSuper = false)
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

	}

	@Data
	@EqualsAndHashCode(callSuper = false)
	@MappedSuperclass
	public static class RolePermissionBase extends AbstractUUIDPersistable {

		@Column(name = "role_id")
		private String roleId;

		@Column(name = "permission_id")
		private String permissionId;

	}

	@Data
	@EqualsAndHashCode(callSuper = false)
	@MappedSuperclass
	public static class UserRoleBase extends AbstractUUIDPersistable {

		@Column(name = "user_id")
		private String userId;

		@Column(name = "role_id")
		private String roleId;

	}

	@Data
	@EqualsAndHashCode(callSuper = false)
	@MappedSuperclass
	public static class RoleBase extends AbstractUUIDPersistable {

		@Column(name = "app_id")
		private String appId;

		@Column(name = "role_name")
		private String roleName;

		@Column(name = "show_name")
		private String showName;

	}

	@Data
	@EqualsAndHashCode(callSuper = false)
	@MappedSuperclass
	public static class PermissionBase extends AbstractUUIDPersistable {

		@Column(name = "app_id")
		private String appId;

		@Column(name = "permission")
		private String permission;

		@Column(name = "show_name")
		private String showName;

	}

	@Data
	@EqualsAndHashCode(callSuper = false)
	@MappedSuperclass
	public static class UserBase extends AbstractAuditable {

		/**
		 * 
		 */
		@ManyToOne(optional = false)
		private SysOrg sosOrg;

		@Column(name = "user_name")
		private String userName;

		@JSONField(serialize = false, deserialize = false)
		@Column(name = "pass_word")
		private String passWord;

		@Column(name = "nick_name")
		private String nickName;

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

	}
}
