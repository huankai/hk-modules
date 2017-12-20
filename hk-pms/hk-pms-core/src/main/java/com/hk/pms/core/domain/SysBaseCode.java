package com.hk.pms.core.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典
 * @author: huangkai
 * @date 2017-11-29 16:27
 */
@Entity
@Table(name = "sys_base_code")
@Data
@EqualsAndHashCode(callSuper = false)
public class SysBaseCode extends ModelHolder.SysBaseCodeBase {


    /**
	 * 
	 */
	private static final long serialVersionUID = -2836976613651239830L;
	
	/**
     *
     */
    @Transient
    private List<SysChildCode> childCodes;

    public List<SysChildCode> getChildCodes() {
        return childCodes;
    }

    public void setChildCodes(List<SysChildCode> childCodes) {
        this.childCodes = childCodes;
    }
}
