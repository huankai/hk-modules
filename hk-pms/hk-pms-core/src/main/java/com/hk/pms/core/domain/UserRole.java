package com.hk.pms.core.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 
 * @author huangkai
 * @date 2017年10月23日下午1:32:24
 */
@Entity
@Table(name = "sys_user_role")
@Data
@EqualsAndHashCode(callSuper = false)
public class UserRole extends ModelHolder.UserRoleBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9154016765677651248L;

	
}
