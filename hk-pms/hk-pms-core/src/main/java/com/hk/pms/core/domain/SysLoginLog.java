package com.hk.pms.core.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author huangkai
 * @date 2017年12月1日下午2:12:06
 */
@Entity
@Table(name = "sys_login_log")
@Data
@EqualsAndHashCode(callSuper = false)
public class SysLoginLog extends ModelHolder.SysLoginLogBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1087685406242790069L;
	
	

}
