package com.hk.pms.core.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 第三方用户
 * 
 * @author huangkai
 * @date 2017年12月1日下午2:18:31
 */
@Entity
@Table(name = "sys_user_third")
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserThird extends ModelHolder.SysUserThirdBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6726131331814942952L;

}
