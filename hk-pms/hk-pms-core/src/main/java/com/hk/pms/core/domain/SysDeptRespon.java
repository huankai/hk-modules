package com.hk.pms.core.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部门责任人
 * @author: huangkai
 * @date 2017-11-30 09:04
 */
@Entity
@Table(name = "sys_dept_respon")
@Data
@EqualsAndHashCode(callSuper = false)
public class SysDeptRespon extends ModelHolder.SysDeptResponBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1981090732650436073L;


}
