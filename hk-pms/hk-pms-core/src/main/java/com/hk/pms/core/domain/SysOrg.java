package com.hk.pms.core.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 机构实体
 * 
 * @author: huangkai
 * @date 2017-11-30 09:04
 */
@Entity
@Table(name = "sys_org")
public class SysOrg extends ModelHolder.SysOrgBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2593297596081849804L;

}
