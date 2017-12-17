package com.hk.pms.core.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.hk.pms.core.domain.ModelHolder.PermissionBase;

/**
 * 权限
 * @author huangkai
 * @date 2017年10月13日下午5:15:33
 */
@Entity
@Table(name = "sys_permission")
public class Permission extends PermissionBase {

	/**
	 *
	 */
	private static final long serialVersionUID = -5763596571965340608L;

}
