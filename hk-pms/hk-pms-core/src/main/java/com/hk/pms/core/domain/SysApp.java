package com.hk.pms.core.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统实体
 *
 * @author huangkai
 * @date 2017-11-24 15:31
 */
@Entity
@Table(name = "sys_app")
@Data
@EqualsAndHashCode(callSuper = false)
public class SysApp extends ModelHolder.SysAppBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9037828573003406698L;


}
