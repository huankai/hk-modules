package com.hk.pms.core.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户设置
 *
 * @author huangkai
 * @date 2017-11-24 15:41
 */
@Entity
@Table(name = "sys_user_setting")
@Data
@EqualsAndHashCode(callSuper = false)
public class UserSetting extends ModelHolder.UserSettingBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4683607953616638196L;

}
