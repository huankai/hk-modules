package com.hk.pms.core.domain;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 
 * @author huangkai
 * @date 2017年10月23日下午1:34:27
 */
@Entity
@Table(name = "sys_role")
public class Role extends ModelHolder.RoleBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5418432385547621686L;

}
