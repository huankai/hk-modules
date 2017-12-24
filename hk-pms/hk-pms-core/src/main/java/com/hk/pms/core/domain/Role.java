package com.hk.pms.core.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author huangkai
 * @date 2017年10月23日下午1:34:27
 */
@Entity
@Table(name = "sys_role")
@Data
@EqualsAndHashCode(callSuper = false)
public class Role extends ModelHolder.RoleBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5418432385547621686L;

	/**
	 * 权限
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = RolePermission.TABLE_NAME, inverseJoinColumns = @JoinColumn(nullable = false,updatable = false,name = "permission_id",referencedColumnName = "id"),
	joinColumns = @JoinColumn(nullable = false, updatable = false, name = "role_id", referencedColumnName = "id"))
	private Set<Permission> permissions;

}
