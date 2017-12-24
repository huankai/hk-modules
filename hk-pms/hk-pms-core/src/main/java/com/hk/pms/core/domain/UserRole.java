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
@Table(name = UserRole.TABLE_NAME)
@Data
@EqualsAndHashCode(callSuper = false)
public class UserRole extends ModelHolder.UserRoleBase {

	static final String TABLE_NAME = "sys_user_role";

	/**
	 * 
	 */
	private static final long serialVersionUID = 9154016765677651248L;

}
