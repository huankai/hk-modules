package com.hk.pms.core.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author: huangkai
 * @date 2017-11-29 17:11
 */
@Entity
@Table(name = "sys_clild_code")
public class SysChildCode extends ModelHolder.SysChildCodeBase {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6562442354700499998L;
	
	@Transient
    private SysBaseCode sysBaseCode;

    public SysBaseCode getSysBaseCode() {
        return sysBaseCode;
    }

    public void setSysBaseCode(SysBaseCode sysBaseCode) {
        this.sysBaseCode = sysBaseCode;
    }
}
