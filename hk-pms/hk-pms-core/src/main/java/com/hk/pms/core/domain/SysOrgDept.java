package com.hk.pms.core.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 机构部门实体
 * 
 * @author: huangkai
 * @date 2017-11-30 09:04
 */
@Entity
@Table(name = "sys_org_dept")
@Data
@EqualsAndHashCode(callSuper = false)
public class SysOrgDept extends ModelHolder.SysOrgDeptBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4308242461215847120L;

}
