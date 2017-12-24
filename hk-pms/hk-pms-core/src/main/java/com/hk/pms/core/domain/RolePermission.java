package com.hk.pms.core.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 
 * @author huangkai
 * @date 2017年10月23日下午1:33:13
 */
@Entity
@Table(name = RolePermission.TABLE_NAME)
@Data
@EqualsAndHashCode(callSuper = false)
public class RolePermission extends ModelHolder.RolePermissionBase {
	
	static final String TABLE_NAME = "sys_role_permission";

	/**
	 * 
	 */
	private static final long serialVersionUID = -7767517255504696179L;

	
}
