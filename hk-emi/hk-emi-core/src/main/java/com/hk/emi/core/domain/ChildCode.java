package com.hk.emi.core.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: huangkai
 * @date 2017-11-29 17:11
 */
@Entity
@Table(name = "sys_clild_code")
@Data
@EqualsAndHashCode(callSuper = false)
public class ChildCode extends ModelHolder.ChildCodeBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6562442354700499998L;


}
