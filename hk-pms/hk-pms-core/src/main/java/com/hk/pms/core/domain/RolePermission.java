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
@Table(name = "sys_role_permission")
@Data
@EqualsAndHashCode(callSuper = false)
public class RolePermission extends ModelHolder.RolePermissionBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7767517255504696179L;

	
}