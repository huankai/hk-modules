package com.hk.pms.core.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.hk.commons.annotations.EnumDisplay;
import com.hk.commons.util.EnumDisplayUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户基本信息
 * 
 * @author huangkai
 * @date 2017年9月22日下午3:05:36
 */
@Entity
@Table(name = "sys_user")
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends ModelHolder.UserBase {

	/**
	 *
	 */
	private static final long serialVersionUID = -2561707826944989624L;
	
	public enum UserStatus {

        /**
         * 禁用
         */
        @EnumDisplay(value = "禁用",order = 0)
        DISABLE,

        /**
         *启用
         */
        @EnumDisplay(value = "启用",order = 1)
        ENABLE,

        /**
         * 锁定
         */
        @EnumDisplay(value = "锁定",order = 9)
        LOCKED
    }

	public boolean locked() {
		final Integer userStatus = getUserStatus();
		return null == userStatus || userStatus.intValue() == EnumDisplayUtils.getDisplayOrder(UserStatus.LOCKED);
	}

}
