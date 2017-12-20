package com.hk.pms.core.domain;

import javax.persistence.Entity;
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

}
